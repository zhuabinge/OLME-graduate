package com.olme.dataSource;

import com.olme.activity.R;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by wxc on 2014/8/21.
 */
public class ChapterInfoData {
    public static LinkedList<HashMap<String,Object>> getDataSource(){
        LinkedList<HashMap<String,Object>> list = new LinkedList<HashMap<String,Object>>();
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("img", R.drawable.java_object);
        map1.put("username", "项目引导");
        map1.put("phone","1");
        map1.put("address","0");
        HashMap<String,Object> map2 = new HashMap<String,Object>();
        map2.put("img", R.drawable.java_object);
        map2.put("username", "认识Java");
        map2.put("phone","1");
        map2.put("address","1");
        HashMap<String,Object> map3 = new HashMap<String,Object>();
        map3.put("img", R.drawable.java_object);
        map3.put("username", "变量及运算符");
        map3.put("phone","8");
        map3.put("address","3");
        HashMap<String,Object> map4 = new HashMap<String,Object>();
        map4.put("img", R.drawable.web);
        map4.put("username", "流程控制语句");
        map4.put("phone","8");
        map4.put("address","4");
        HashMap<String,Object> map5 = new HashMap<String,Object>();
        map5.put("img", R.drawable.java_object);
        map5.put("username", "数组");
        map5.put("phone","7");
        map5.put("address","24");
        HashMap<String,Object> map6 = new HashMap<String,Object>();
        map6.put("img", R.drawable.java_object);
        map6.put("username", "方法定义及调用");
        map6.put("phone","8");
        map6.put("address","33");
        HashMap<String,Object> map7 = new HashMap<String,Object>();
        map7.put("img", R.drawable.java_object);
        map7.put("username", "面向对象基础");
        map7.put("phone","5");
        map7.put("address","50");
        HashMap<String,Object> map8 = new HashMap<String,Object>();
        map8.put("img", R.drawable.java_object);
        map8.put("username", "面向对象高级特性");
        map8.put("phone","17");
        map8.put("address","15");
        HashMap<String,Object> map9 = new HashMap<String,Object>();
        map9.put("img", R.drawable.java_object);
        map9.put("username", "异常处理");
        map9.put("phone","6");
        map9.put("address","40");
        HashMap<String,Object> map10 = new HashMap<String,Object>();
        map10.put("img", R.drawable.spring);
        map10.put("username", "工具类");
        map10.put("phone","13");
        map10.put("address","30");


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
