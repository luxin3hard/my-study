package com.study.luxin.netty.iodemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * https://www.cnblogs.com/yiwangzhibujian/p/7107785.html
 * demo存在的问题:
 * 1.每个socket连接建立,都会启动一个线程,在连接建立前,会阻塞住.
 * 2.大量线程,线程频繁切换,损耗性能.
 * 3.IO编程,字节流为单位,效率不高.
 */
public class IOServer {

    /**
     * 使用线程池优点:
     * 1.避免创建大量线程,使资源耗尽.
     * 2.线程复用,避免新建销毁代价.
     */
    private static ExecutorService threadPool = Executors.newFixedThreadPool(100);


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        // 如果设置了超时时间,会影响ServerSocket.accept() SocketInputStream.read() DatagramSocket.receive()
        serverSocket.setSoTimeout(100);

        new Thread(() -> {
            while (true) {
                try {
                    // 连接前,阻塞住.
                    // 判断socket是否可以使用的判断逻辑是:
                    // socket != null && socket.isBound() && !socket.isClosed() && socket.isConnected()&& !socket.isInputShutdown() && !socket.isOutputShutdown()
                    Socket socket = serverSocket.accept();

                    socket.setReuseAddress(false);
                    System.out.println("获取了连接: " + System.currentTimeMillis());

                    doReceive(socket);
                } catch (IOException e) {
                }
            }
        }).start();
    }

    private static void doReceive(Socket socket) {
        threadPool.submit(() -> {
            byte[] data = new byte[1024];
            try {
                InputStream inputStream = socket.getInputStream();
                TimeUnit.SECONDS.sleep(1);
                while (true) {
                    int len;

                    /**
                     * inputStream.read() 方法
                     * 1. 如果会阻塞知道等到获取到客户端发送的数据.
                     * 2. 如果到了文件的尾部,返回 -1
                     */
                    while ((len = inputStream.read(data)) != -1) {
                        System.out.println(new String(data, 0, len));

                        OutputStream outputStream = socket.getOutputStream();

                        System.out.println("remote port bind: "+socket.getPort());

                        PrintWriter printWriter = new PrintWriter(outputStream);
                        printWriter.print("收到消息!!!");
                        outputStream.flush();
                        printWriter.close();
                    }
                }
            } catch (IOException | InterruptedException e) {
            }
        });
    }


}
