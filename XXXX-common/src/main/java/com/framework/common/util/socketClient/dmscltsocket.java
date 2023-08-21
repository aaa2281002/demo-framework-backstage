package com.framework.common.util.socketClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class dmscltsocket extends Thread {
    private final String host;
    private final int port;
    private ReaderThread readerThread;
    public SocketChannel socket;
    private final NumberFormat formatter = new DecimalFormat("00000");
    private ExecutorService executorService;

    private long _QueryTimeout = 3;

    public boolean IsConnect = false;

    public dmscltsocket(String host, int port, long iQueryTimeout) {
        this.host = host;
        this.port = port;
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        _QueryTimeout = iQueryTimeout;
    }


    @Override
    public void run() {
        try {
            socket = SocketChannel.open();
            socket.configureBlocking(false);
            socket.connect(new InetSocketAddress(host, port));
            while (!socket.finishConnect()) {
                IsConnect = false;

            }
            if (readerThread != null && !readerThread.isInterrupted()) {
                readerThread.interrupt();
            }
            readerThread = new ReaderThread(socket);
            readerThread.start();

//            boolean cmd = true;
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < (cmd ? 2040 : 2041); i++) {
//                sb.append("a");
//            }
//            sb.append("xac");
//            sendData(sb.toString(), cmd);

            while (socket != null && socket.isOpen()) {
                System.out.println("连接成功");
                IsConnect = true;
                sendData("keepalive", false);
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            IsConnect = false;
            e.printStackTrace();
        }
    }

    public void sendData(String data) {
        sendData(data, false);
    }

    public void sendCmd(String msg) {
        sendData(msg, true);
    }

    /**
     * 发送数据，发送失败返回false,发送成功返回true
     *
     * @param message
     * @return
     */
    public Boolean Send(String message) {

        try {

            queryData(message, 1);

            return true;

        } catch (Exception se) {

            se.printStackTrace();

            return false;

        }

    }

    @Deprecated
    public String queryData(String snMsg, long delay) {
        long timeMillis = System.currentTimeMillis();
        sendData(snMsg);
        try {
            if (_QueryTimeout <= 0) _QueryTimeout = Long.MAX_VALUE;
            Object result = executorService.submit(() -> {
                while (readerThread.lastResp.get() <= timeMillis) {
                }
                return readerThread.result.get();
            }).get(_QueryTimeout, TimeUnit.SECONDS);//单位毫秒
            if (delay > 0) Thread.sleep(delay);
            return String.valueOf(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendData(String msg, boolean cmd) {
        if (socket == null || !socket.isConnected()) return;
        executorService.submit(() -> {
            byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
            int sliceSize = 0;
            if (cmd) {
                sliceSize = 2040;
            } else {
                sliceSize = 2041;
            }
            int sliceLast = bytes.length / sliceSize;

            ByteBuffer buffer = ByteBuffer.allocate(2048);
            try {
                for (int i = 0; i <= sliceLast; i++) {
                    buffer.clear();
                    buffer.put((byte) '@');
                    if (i == sliceLast) {
                        if (cmd) {
                            buffer = ByteBuffer.allocate(bytes.length % sliceSize + 8);
                        } else {
                            buffer = ByteBuffer.allocate(bytes.length % sliceSize + 7);
                        }
                        buffer.put((byte) '@');
                        buffer.put(formatter.format(bytes.length % sliceSize).getBytes(StandardCharsets.UTF_8));
                        buffer.put((byte) '#');
                    } else {
                        buffer.put(formatter.format(sliceSize).getBytes(StandardCharsets.UTF_8));
                        buffer.put((byte) '$');
                    }
                    if (cmd) buffer.put((byte) '%');
                    buffer.put(bytes, i * sliceSize, Math.min(sliceSize, bytes.length - i * sliceSize));
                    write(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void write(ByteBuffer buffer) {
        try {
            buffer.flip();
            while (buffer.hasRemaining()) socket.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (socket == null) return;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (readerThread != null && !readerThread.isInterrupted()) readerThread.interrupt();
        executorService.shutdownNow();
        interrupt();
    }


    private static class ReaderThread extends Thread {
        private final SocketChannel socket;
        private final AtomicReference<String> result = new AtomicReference<>();
        private final AtomicLong lastResp = new AtomicLong();

        public ReaderThread(SocketChannel socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ByteArrayOutputStream baos = null;
            try {
                ByteBuffer buffer = null;
                ByteBuffer headerBuffer = ByteBuffer.allocate(8);
                int length = 0;
                String header;
                while (socket != null && socket.isOpen()) {
                    if (buffer != null) buffer.clear();
                    headerBuffer.clear();
                    while (socket.isOpen() && socket.read(headerBuffer) > 0) {
                        if (baos == null) baos = new ByteArrayOutputStream();
                        headerBuffer.flip();
                        header = new String(headerBuffer.array());
                        try {
                            length = Integer.parseInt(header.substring(1, 6));
                        } catch (Exception e) {
                            e.printStackTrace();
                            baos.close();
                            baos = null;
                            break;
                        }

                        if (headerBuffer.get(7) != '%') {
                            baos.write(headerBuffer.get(7));
                            if (length == 1) break;
                            length -= 1;
                        }
                        headerBuffer.clear();
                        buffer = ByteBuffer.allocate(length);
                        buffer.clear();
                        if (!socket.isOpen() || socket.read(buffer) <= 0) {
                            baos.close();
                            baos = null;
                            break;
                        }
                        buffer.flip();

                        baos.write(buffer.array());
                        if (headerBuffer.get(6) == '#') {
                            break;
                        }
                    }
                    if (baos == null) continue;

                    result.set(baos.toString("UTF-8").replaceAll("\\u0000", ""));
                    lastResp.set(System.currentTimeMillis());

                    System.out.printf("length: %d, data: %s\n", result.get().length(), result.get());
                    baos.close();
                    baos = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (baos != null) baos.close();
                    baos = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("exit read");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        dmscltsocket server = new dmscltsocket("120.197.79.122", 7878, 3);
        server.start();
        Thread.sleep(3000);
        String res = server.queryData("zzzzzz", 3);
        System.out.println(res);
    }
}
