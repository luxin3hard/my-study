package json;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.*;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Objects;

public class FastJsonParseInterface {

    @Test
    public void fastJsonParseInterface() {
        String json = "{\"name\":\"luxin\",\"age\":18}";

        // 即使duck没有实现类, fastJson会生成代理类,只要有 key对应的 set+Key的方法就可以
        Duck duck = JSONObject.parseObject(json, Duck.class);
        assert Objects.equals(duck.getName(), "luxin");
    }


    @Test
    public void notSetAge() {
        // json的key和set直接相关, 会找 set+key的那个方法set值
        String json = "{\"name\":\"luxin\",\"wightL\":18}";
        BigDuck duck = JSONObject.parseObject(json, BigDuck.class);
        // 和get方法无关
        assert Objects.equals(duck.getWight(), 18);
    }

    @Test
    public void gsonParseInterface() {
        String json = "{\"name\":\"luxin\",\"age\":18}";
        Gson gson = new GsonBuilder().create();

        // gson必须找到实现类才能做反序列化
        try {
            Duck duck = gson.fromJson(json, Duck.class);
        } catch (Exception e) {
            // json 不能处理接口类型
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(Duck.class, new DuckDeserializer());
        Duck duck = gsonBuilder.create().fromJson(json, Duck.class);
        assert Objects.equals(duck.getName(), "luxin");
    }

    private static class DuckDeserializer implements JsonDeserializer<Duck> {

        @Override
        public Duck deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (!jsonElement.isJsonObject()) {
                return null;
            }
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Duck duck = new BigDuck();

            if (jsonObject.has("name")) {
                duck.setName(jsonObject.get("name").getAsString());
            }

            if (jsonObject.has("age")) {
                duck.setAge(jsonObject.get("age").getAsInt());
            }
            return duck;
        }
    }


}
