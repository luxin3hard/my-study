package com.study.luxin.mylist;

import java.util.ArrayList;
import java.util.List;

public class SublistDemo {

    /**
     * 不能对原来的list做修改,如果对原来的 list进行修改,在次访问sublist就会抛出错误
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> sourceList = new ArrayList<String>() {{
            add("H");
            add("O");
            add("L");
            add("L");
            add("I");
            add("S");
        }};

        List subList = sourceList.subList(2, 5);
        /**
         * but对sublist进行操作会不导致源list出错
         * 且数据是插在sublist的尾部,插在源list的中间
         */
        subList.add("1212");

        System.out.println("sourceList ： " + sourceList);
        System.out.println("sourceList.subList(2, 5) 得到List ：");
        System.out.println("subList ： " + subList);

        sourceList.add("666");

        System.out.println("sourceList.add(666) 得到List ：");
        System.out.println("sourceList ： " + sourceList);

        /**
         * 任何形式的访问都不行,下面的两个方法都会抛出异常
         */
        System.out.println("subList ： " + subList.get(1));
        System.out.println("subList ： " + subList);

    }


}
