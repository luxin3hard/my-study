package com.study.luxin.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TempCases {


    @Test
    public void test() throws IOException {
        FileOpt fileOpt = new FileOpt();
        String jsonStr = fileOpt.readFile("new_option");

        JSONArray options = JSONObject.parseArray(jsonStr);


        Map<String/*label*/, JSONObject/*option*/> label2Option = Maps.newHashMap();
        Map<String/*value*/, JSONObject/*option*/> value2Option = Maps.newHashMap();
        for (Object option : options) {
            JSONObject jsonObject = (JSONObject) option;
            label2Option.put(jsonObject.getString("label"), jsonObject);
            label2Option.put(jsonObject.getString("value"), jsonObject);
        }


        String jsonStr1 = fileOpt.readFile("cases_option");

        JSONArray newOptions = JSONObject.parseArray(jsonStr1);


        List<JSONObject> notInValue = Lists.newArrayList();
        List<JSONObject> notInLabel = Lists.newArrayList();
        List<JSONObject> notInLabelAndValue = Lists.newArrayList();

        List<String> notInLabelAndValueStrList = Lists.newArrayList();

        for (Object newOption : newOptions) {
            JSONObject jsonObject = (JSONObject) newOption;
            String label = jsonObject.getString("label");
            String value = jsonObject.getString("value");

            if (label2Option.get(label) == null) {
                notInLabel.add(jsonObject);
            }

            if (label2Option.get(value) == null) {
                notInValue.add(jsonObject);
            }


            if (label2Option.get(label) == null && label2Option.get(value) == null) {
                notInLabelAndValue.add(jsonObject);
                notInLabelAndValueStrList.add(label);
            }
        }

        System.out.println(JSON.toJSONString(notInLabelAndValue));


    }


}
