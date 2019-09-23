package com.study.luxin;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by lx on 13/01/2018.
 */
public class MyTest {

    @Test
    public void test() throws IOException {
        URL resource = getClass().getClassLoader().getResource("en.text");
        File file = new File(resource.getFile());


        FileInputStream inputStream = new FileInputStream(file);


        String str = "";

        int i;

        byte[] bytes = new byte[1024];
        while ((i = inputStream.read(bytes)) != -1) {
            str += new String(bytes, "utf-8");
            bytes = new byte[1024];

        }


        for (int j = 16; j <= 35; j++) {

            str = str.replaceAll(j + "", j - 15 + "");

        }

        for (int k = 1; k <= 20; k++) {
            str = str.replaceFirst(k + "", "（" + k + "）");
        }


        System.out.println(str);


    }


}
