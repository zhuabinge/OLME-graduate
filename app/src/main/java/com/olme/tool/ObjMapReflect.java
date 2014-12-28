package com.olme.tool;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
                if (m[i].getName().indexOf("get")==0) {
                    //System.out.println("方法名："+m[i].getName());
                    // System.out.println("值："+ m[i].invoke(obj, new Object[0]));
                    map.put(m[i].getName(), m[i].invoke(obj, new Object[0]));
                }
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
        return map;
    }
}
