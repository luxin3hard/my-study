package json;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonParse {


    public void parse(){

        JSON.parseObject("", JsonParse.class);
        Gson gson = new GsonBuilder().create();

        List<JsonParse> ps = gson.fromJson("", new TypeToken<List<JsonParse>>(){}.getType());


    }
}
