package com.olme.tool;


import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Bingo on 2014/8/14.
 * 反射，将Object转换成HashMap
 */

public class ObjMapReflect {
    public static HashMap<String,Object> changeToHashMap(Object obj){
        HashMap<String,Object> map = new HashMap<String,Object>();

        try {
            Class c = obj.getClass();
            Method m[] = c.getDeclaredMethods();
            for (int i = 0; i < m.length; i++) {
                if (m[i].getName().startsWith("get")) {
                    String field = m[i].getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    map.put(field, m[i].invoke(obj, new Object[0]));
                }
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
        System.out.println("javabean to map" + map);
        return map;
    }
}
