package com.study.luxin.syn;


// 可以通过 javap -v xxx.class 查看类的字节码
public class BlockMethodSynchronizedDiff {

    public void blockSyn() {

        synchronized (this) {
        }
    }


    public synchronized void methodSyn() {

    }

    public static void main(String[] args) {
        System.out.println("");
    }


}
