package com.olme.dataSource;

import com.olme.activity.R;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/16.
 */
public class MyCollectionData {
    public static LinkedList<HashMap<String, Object>> getDataSource() {
        LinkedList<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("getVedioId", "1");
        map1.put("getVedioName", "项目引导");
        map1.put("getVedioLength", "45");
        map1.put("rating", "5");
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("getVedioId", "2");
        map2.put("getVedioName", "认识Java");
        map2.put("getVedioLength", "50");
        map2.put("rating", "0");
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("getVedioId", "3");
        map3.put("getVedioName", "变量及运算符");
        map3.put("getVedioLength", "46");
        map3.put("rating", "5");
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("getVedioId", "4");
        map4.put("getVedioName", "流程控制语句");
        map4.put("getVedioLength", "34");
        map4.put("rating", "5");
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("getVedioId", "5");
        map5.put("getVedioName", "数组");
        map5.put("getVedioLength", "23");
        map5.put("rating", "0");
        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("getVedioId", "6");
        map6.put("getVedioName", "方法定义及调用");
        map6.put("getVedioLength", "34");
        map6.put("rating", "0");
        HashMap<String, Object> map7 = new HashMap<String, Object>();
        map7.put("getVedioId", "7");
        map7.put("getVedioName", "面向对象基础");
        map7.put("getVedioLength", "45");
        map7.put("rating", "5");
        HashMap<String, Object> map8 = new HashMap<String, Object>();
        map8.put("getVedioId", "8");
        map8.put("getVedioName", "面向对象高级特性");
        map8.put("getVedioLength", "65");
        map8.put("rating", "0");
        HashMap<String, Object> map9 = new HashMap<String, Object>();
        map9.put("getVedioId", "9");
        map9.put("getVedioName", "垃圾回收机制");
        map9.put("getVedioLength", "54");
        map9.put("rating", "5");
        HashMap<String, Object> map10 = new HashMap<String, Object>();
        map10.put("getVedioId", "10");
        map10.put("getVedioName", "文件读写");
        map10.put("getVedioLength", "43");
        map10.put("rating", "0");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);
        return list;
    }
}
