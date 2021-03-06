package com.study.luxin.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FileOpt {


    public String readFile(String fileName) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResource(fileName));
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
        List<String> supportBiFuncCode = Lists.newArrayList("20001", "20002", "20003", "20004", "20005", "20006", "20007", "20008", "20009", "20010", "20011", "20012", "20014", "20017", "20018", "20019", "20020", "100601", "100602", "100603", "100604", "100605", "100606", "100607", "100608", "20013", "20015", "20016", "20021", "20022");

        List<Object> result = Lists.newArrayList();
        for (Object funcInfo : JSON.parseArray(roleJsonStr)) {
            JSONObject tmp = (JSONObject) funcInfo;
            String funcCode = tmp.get("FunctionNo").toString();
            if (!supportBiFuncCode.contains(funcCode)) {
                result.add(funcInfo);
            }
        }


        System.out.println(result);
    }

    @Test
    public void test1() throws IOException {
        String roleJsonStr = readFile("product_service_code");
        List<String> list = Lists.newArrayList();
        String str = "update mt_data d set value3 = '%s' from biz_product p where p.tenant_id = d.tenant_id and d.tenant_id = '598335' and p.extend_obj_data_id = d.id and p.name='%s';\n";


        String[] lines = roleJsonStr.split("\\n");

        for (String line : lines) {

            String[] s = line.split("#");

            list.add(String.format(str, s[1], s[0]) + "\n");

        }

        System.out.println(list);
    }


    @Test
    public void fileLinesStreamTest() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("index.html");


        Path path = Paths.get(resource.toURI());
        Stream<String> lines = Files.lines(path);
        lines.forEach(x -> System.out.println("0--" + x));
    }


}
