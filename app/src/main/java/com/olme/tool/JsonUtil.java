package com.olme.tool;

import com.google.gson.Gson;

/**
 * Created by root on 15-3-10.
 */
public class JsonUtil {

    public static <T> T fromObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            System.out.println("jsonString:" + jsonString);
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }
}
