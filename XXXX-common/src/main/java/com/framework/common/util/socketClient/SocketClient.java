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

public class SocketClient extends Thread {
    private final String host;
    private final int port;
    private SocketClient.ReaderThread readerThread;
    private SocketChannel socket;
    private final NumberFormat formatter = new DecimalFormat("00000");
    private ExecutorService executorService;


    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }

    public static void main(String[] args) throws InterruptedException {
        SocketClient SocketClient = new SocketClient("42.192.47.120", 9088);
        SocketClient.start();

        Thread.sleep(3000);
        String res = SocketClient.queryData("aaaaaaaaaa", 0, 0);
        System.out.println("result: " + res);
//        Thread.sleep(11_000);
//        SocketClient.close();
//        System.out.println("exit");
    }


    @Override
    public void run() {
        try {
            socket = SocketChannel.open();
            socket.configureBlocking(false);
            socket.connect(new InetSocketAddress(host, port));
            while (!socket.finishConnect()) {
            }
            if (readerThread != null && !readerThread.isInterrupted()) {
                readerThread.interrupt();
            }
            readerThread = new SocketClient.ReaderThread(socket);
            readerThread.start();

//            boolean cmd = true;
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < (cmd ? 2040 : 2041); i++) {
//                sb.append("a");
//            }
//            sb.append("xac");
//            sendData(sb.toString(), cmd);

            while (socket != null && socket.isOpen()) {
                sendData("keepalive", false);
                try {
                    sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(String data) {
        sendData(data, false);
    }

    public void sendCmd(String msg) {
        sendData(msg, true);
    }


    @Deprecated
    public String queryData(String snMsg, long delay, long timeOut) {
        long timeMillis = System.currentTimeMillis();
        sendData(snMsg);
        try {
            if (timeOut <= 0) timeOut = Long.MAX_VALUE;
            Object result = executorService.submit(() -> {
                while (readerThread.lastResp.get() <= timeMillis) {
                }
                return readerThread.result.get();
            }).get(timeOut * 1000, TimeUnit.MILLISECONDS);//单位毫秒
            if (delay > 0) Thread.sleep(delay);
            return result.toString();
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

                    lastResp.set(System.currentTimeMillis());
                    result.set(baos.toString("utf-8"));
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
}
