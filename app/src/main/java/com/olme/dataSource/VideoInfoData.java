package com.olme.dataSource;

import com.olme.activity.R;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by wxc on 2014/8/21.
 */
public class VideoInfoData {
    public static LinkedList<HashMap<String,Object>> getDataSource(){
        LinkedList<HashMap<String,Object>> list = new LinkedList<HashMap<String,Object>>();
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("img", R.drawable.java_object);
        map1.put("username", "if语句");
        map1.put("phone","5");
        map1.put("address","0");
        HashMap<String,Object> map2 = new HashMap<String,Object>();
        map2.put("img", R.drawable.oracledatabase);
        map2.put("username", "switch语句");
        map2.put("phone","10");
        map2.put("address","1");
        HashMap<String,Object> map3 = new HashMap<String,Object>();
        map3.put("img", R.drawable.javase);
        map3.put("username", "while循环");
        map3.put("phone","20");
        map3.put("address","3");
        HashMap<String,Object> map4 = new HashMap<String,Object>();
        map4.put("img", R.drawable.web);
        map4.put("username", "dowhile循环");
        map4.put("phone","20");
        map4.put("address","4");
        HashMap<String,Object> map5 = new HashMap<String,Object>();
        map5.put("img", R.drawable.oraclepl);
        map5.put("username", "for循环");
        map5.put("phone","23");
        map5.put("address","24");
        HashMap<String,Object> map6 = new HashMap<String,Object>();
        map6.put("img", R.drawable.javaweb);
        map6.put("username", "无限循环");
        map6.put("phone","44");
        map6.put("address","33");
        HashMap<String,Object> map7 = new HashMap<String,Object>();
        map7.put("img", R.drawable.ajax);
        map7.put("username", "循环中断");
        map7.put("phone","15");
        map7.put("address","50");
        HashMap<String,Object> map8 = new HashMap<String,Object>();
        map8.put("img", R.drawable.struts2);
        map8.put("username", "章节测试");
        map8.put("phone","40");
        map8.put("address","15");


        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        return list;
    }
}
