package com.study.luxin.netty.iodemo;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class IOClient {

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1", 8000);
                    OutputStream outputStream = socket.getOutputStream();

                    outputStream.write(String.format("luxin socket.%s %s", Thread.currentThread(), System.currentTimeMillis()).getBytes());
                    outputStream.flush();
                    TimeUnit.SECONDS.sleep(5);

                    InputStream inputStream = socket.getInputStream();

                    BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        System.out.println(serverMsg);
                    }
                } catch (IOException | InterruptedException e) {
                }
            }).start();
        }
    }
}
