package com.study.luxin.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileOpt {


    public String readFile(String filePath) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResource(filePath));
    }

    @Test
    public void test() throws IOException {
        String fileStr = readFile("hetong");

        JSONObject ob = JSON.parseObject(fileStr);
        JSONArray array = (JSONArray) ob.get("Value");

        Map<String, String> name2FiledName = Maps.newHashMap();
        for (Object item : array) {
            JSONObject item1 = (JSONObject) item;
            name2FiledName.put(item1.get("fieldCaption").toString(), item1.get("fieldName").toString());

        }

        String orderedFieldStr = "合同编号、合同标题、客户名称、开始日期、截止日期、合同金额（元）、业务类型、生命状态、锁定状态、负责人、负责人所在部门、归属部门、外部负责人、创建人、创建时间、最后修改人、最后修改时间";
        String[] orderedFields = orderedFieldStr.split("、");
        int a = 0;
        for (String orderedField : orderedFields) {
            String filedName = name2FiledName.get(orderedField);
            if (filedName == null) {
                System.out.println("------------------" + orderedField);
            } else {
                System.out.println(filedName);
            }
        }
    }


    @Test
    public void parseRoleJson() throws IOException {
        String roleJsonStr = readFile("role_json");
        List<String> supportBiFuncCode = Lists.newArrayList("100101", "100102", "100103", "100104", "100105", "100106", "100107", "100108", "100201", "100202", "100203", "100204", "100205", "100206", "100207", "100208", "100301", "100302", "100303", "100304", "100305", "100306", "100307", "100308", "100401", "100402", "100403", "100404", "100405", "100406", "100407", "100408", "100501", "100502", "100503", "100504", "100505", "100506", "100507", "100508", "100601", "100602", "100603", "100604", "100605", "100606", "100607", "100608", "109901", "109902", "109903", "109904", "109905", "109906", "109907", "109908", "100701", "100702", "100703", "100704", "100705", "100706", "100707", "100708", "109001", "109002", "109003", "109004", "109005", "109006", "109007", "109008", "100801", "100802", "100803", "100804", "100805", "100806", "100807", "100808");

        List<Object> result = Lists.newArrayList();
        for (Object funcInfo : JSON.parseArray(roleJsonStr)) {
            JSONObject tmp = (JSONObject) funcInfo;
            String funcCode = tmp.get("FunctionNo").toString();
            if (supportBiFuncCode.contains(funcCode)) {
                result.add(funcInfo);
            }
        }


        System.out.println(result);
    }


}
