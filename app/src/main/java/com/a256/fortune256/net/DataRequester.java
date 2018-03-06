package com.a256.fortune256.net;

import android.util.Log;

import com.a256.fortune256.Config;
import com.a256.fortune256.debug.T;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.show.api.ShowApiRequest;

/**
 * Created by de165 on 2018/2/8.
 */

public class DataRequester {
    public static Object getSSQ(){
        String json = new ShowApiRequest("http://route.showapi.com/44-1",Config.APP_ID,Config.Secret)
                .addTextPara("code","ssq")
                .post();

        JsonElement jsonElem = new JsonParser().parse(json);
        JsonObject jsonObj = jsonElem.getAsJsonObject();
        JsonElement res = jsonObj.get("showapi_res_body");
        JsonObject jsonArrObj = res.getAsJsonObject();
        JsonArray jsonArray = jsonArrObj.getAsJsonArray("result");
        JsonObject result = jsonArray.get(0).getAsJsonObject();

        return result;
    }

    public static Object getNewest(){
        String json = new ShowApiRequest("http://route.showapi.com/44-1",Config.APP_ID,Config.Secret)
                .addTextPara("code","")
                .post();

        JsonElement jsonElem = new JsonParser().parse(json);
        JsonObject jsonObj = jsonElem.getAsJsonObject();
        JsonElement res = jsonObj.get("showapi_res_body");
        JsonObject jsonArrObj = res.getAsJsonObject();
        JsonArray jsonArray = jsonArrObj.getAsJsonArray("result");

        return jsonArray;
    }

    public static Object getFortune(String count){
        String json = new ShowApiRequest("http://route.showapi.com/44-2",Config.APP_ID,Config.Secret)
                .addTextPara("code","cqssc")
                .addTextPara("endTime","")
                .addTextPara("count",count)
                .post();

        JsonElement jsonElem = new JsonParser().parse(json);
        JsonObject jsonObj = jsonElem.getAsJsonObject();
        JsonElement res = jsonObj.get("showapi_res_body");
        JsonObject jsonArrObj = res.getAsJsonObject();
        JsonArray jsonArray = jsonArrObj.getAsJsonArray("result");

        return jsonArray;
    }

}
