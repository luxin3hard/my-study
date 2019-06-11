package com.study.luxin.syn.connect;

import org.junit.Test;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.TimeUnit;

/**
 * 通过管道实现线程间通信
 */
public class Piped {

    @Test
    public void pipedDemo() throws IOException, InterruptedException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();

        // 需要连接上才能通信,如果访问没有连接的流,会报错
        out.connect(in);

        Thread t1 = new Thread(new PrintRunner(in));
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        out.write("hello world!");
        TimeUnit.SECONDS.sleep(1);

    }

    static class PrintRunner implements Runnable {
        PipedReader in;

        public PrintRunner(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int a = 0;
            while (true) {
                try {
                    if (!((a = in.read()) != -1)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println((char) a);
            }
        }
    }


}
