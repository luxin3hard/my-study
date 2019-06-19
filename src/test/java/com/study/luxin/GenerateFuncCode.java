package com.study.luxin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.study.luxin.file.FileOpt;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GenerateFuncCode {


    @Test
    public void invokeAddFunc() throws IOException {
        FileOpt fileOpt = new FileOpt();
        String funcCodeInfo = fileOpt.readFile("funcCodeInfo");
        Map parse = (Map) JSONObject.parse(funcCodeInfo);
        Map<String, List<Map>> result = Maps.newHashMap();
        List<Map> funcCodeList = Lists.newArrayList();
        result.put("functionPojoList", funcCodeList);


        List<Object> funcCodes = Lists.newArrayList();
        parse.forEach((k, v) -> {
            Map map = Maps.newHashMap();
            map.put("parentCode", "00000000000000000000000000000000");
            map.put("funcType", 1);
            map.put("appId", "CRM");
            map.put("tenantId", "499793");
            map.put("funcName", v);
            map.put("funcCode", k);

            funcCodes.add(k);

            funcCodeList.add(map);
        });

        System.out.println(JSON.toJSONString(result));

        System.out.println("\n\n\n\n");
        System.out.println(JSON.toJSONString(funcCodes));
    }


}
