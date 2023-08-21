package com.framework.common.util.socketClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClienta extends Thread {
    private final String host;
    private final int port;
    private Thread readerThread;
    private SocketChannel socket;
    private final NumberFormat formatter = new DecimalFormat("00000");
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private int queryTimeout = 3;

    public SocketClienta(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public SocketClienta(String host, int port, int queryTimeout) {
        this.host = host;
        this.port = port;
        this.queryTimeout = queryTimeout;
    }

    public static void main(String[] args) {
        SocketClienta socketClient = new SocketClienta("42.192.47.120", 9088);
        socketClient.start();
    }

    @Override
    public void start() {
        try {
            //开启socket
            socket = SocketChannel.open();
            //设置不阻塞
            socket.configureBlocking(false);
            //连接
            socket.connect(new InetSocketAddress(host, port));
            //等待连接成功 // false尝试重新连接，  true成功就跳过执行下一步
            while (!socket.finishConnect()) {
            }
            if (readerThread != null && readerThread.isAlive()) {
                readerThread.interrupt();
            }
            //创建一个读线程
            readerThread = new ReaderThread(socket, queryTimeout);
            readerThread.start();
            while (socket.isOpen()) {
                sendData("啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊" +
                        "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
//                sendCmd("keepalive");
                try {
                    sleep(10_000);//单位毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    //发送消息
    public void sendData(String data) {
        sendData(data, 7);
    }

    //发送命令
    public void sendCmd(String msg) {
        sendData(msg, 8);
    }

    private void sendData(String msg, int lengthCount) {
        if (socket == null || !socket.isConnected()) return;
        executorService.submit(() -> {
            byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
            //分配缓冲区空间:创建一个字节缓冲区，申请内存空间为 bytes.length + lengthCount 字节
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length + lengthCount);
            buffer.put((byte) '@');
            buffer.put(formatter.format(bytes.length).getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) '$');
            if (lengthCount == 8) buffer.put((byte) '%');
            buffer.put(bytes);
            for (int i = 0; i < (bytes.length / 2040F) - 1; i++) {
                write(buffer);
            }
            buffer.put(6, (byte) '#');
            System.out.println("lengthCount=" + lengthCount + ", buffer = " + new String(buffer.array()));
            write(buffer);
        });
    }

    private void write(ByteBuffer buffer) {
        try {
            //缓冲区的反转：转为有效数组   limit=position position=0 有效数据为position-limit
            buffer.flip();
            //position()：返回当前position的值
            //limit()：返回当前limit的值
            //capacity()：返回当前capacity的值
            //hasRemaining()：返回position和limit之间是否有元素 即： return position < limit
            //remaining()：返回position和limit之间的元素数  即：return limit - position
            while (buffer.hasRemaining()) socket.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关闭方法
    public void close() {
        if (socket == null) {
            return;
        }
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (readerThread != null && !readerThread.isInterrupted()) {
            readerThread.interrupt();
        }
        executorService.shutdownNow();
        interrupt();
    }


    private static class ReaderThread extends Thread {
        private int timeNum = 3;
        private final SocketChannel socket;
        //分配缓冲区空间:创建一个字节缓冲区，申请内存空间为8字节
        private final ByteBuffer headerBuffer = ByteBuffer.allocate(8);

        public ReaderThread(SocketChannel socket) {
            this.socket = socket;
        }

        public ReaderThread(SocketChannel socket, int timeNum) {
            this.socket = socket;
            this.timeNum = timeNum;
        }


        @Override
        public void run() {
            try {

                while (socket != null && socket.isConnected()) {
//                    System.out.println("1===" + DateUtil.getDateToString(new Date(System.currentTimeMillis()), null));
                    Thread.sleep(1000 * timeNum);
//                    System.out.println("2===" + DateUtil.getDateToString(new Date(System.currentTimeMillis()), null));
                    headerBuffer.clear();
                    if (socket.read(headerBuffer) <= 0) continue;
                    String length = new String(headerBuffer.array()).substring(1, 6);
                    System.out.println("test====" + new String(headerBuffer.array()));
//                    try {
                    ByteBuffer buffer = ByteBuffer.allocate(Integer.parseInt(length));
                    socket.read(buffer);
                    System.out.printf("length: %d, data: %s%n", Integer.parseInt(length),
                            new String(buffer.array()));
//                    } catch (Exception e) {
//                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
