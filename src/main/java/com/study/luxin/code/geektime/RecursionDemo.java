package com.study.luxin.code.geektime;

import java.util.Map;

// 递归主题
public class RecursionDemo {

    /**
     * 最终推荐人
     */
    public String finalPerson(Map<String, String> map, String child) {
        String father = map.get(child);
        if (father == null) {
            return null;
        } else {

            return finalPerson(map, father);
        }
    }

}
