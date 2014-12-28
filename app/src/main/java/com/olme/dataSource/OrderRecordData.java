package com.olme.dataSource;

import com.olme.activity.R;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bingo on 2014/8/15.
 * （测试）作为listview中item的数据源
 */
public class OrderRecordData {
    public static LinkedList<HashMap<String, Object>> getDataSource() {
        LinkedList<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("getOrderId", "201307221922");
        map1.put("getCourseName", "Java面向对象开发设计");
        map1.put("getCoursePrice", "200");
        map1.put("getOrderDate", "2013-07-22");
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("getOrderId", "201402092214");
        map2.put("getCourseName", "Oracl数据库技术");
        map2.put("getCoursePrice", "120");
        map2.put("getOrderDate", "2014-02-09");
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("getOrderId", "201403101023");
        map3.put("getCourseName", "JavaSE核心技术");
        map3.put("getCoursePrice", "234");
        map3.put("getOrderDate", "2014-03-10");
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("getOrderId", "201404200920");
        map4.put("getCourseName", "OraclPL实践");
        map4.put("getCoursePrice", "234");
        map4.put("getOrderDate", "2014-04-20");
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("getOrderId", "201404250946");
        map5.put("getCourseName", "Java Web实践");
        map5.put("getCoursePrice", "455");
        map5.put("getOrderDate", "2014-04-25");
        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("getOrderId", "201404290319");
        map6.put("getCourseName", "Ajx架构与实践");
        map6.put("getCoursePrice", "234");
        map6.put("getOrderDate", "2014-05-01");
        HashMap<String, Object> map7 = new HashMap<String, Object>();
        map7.put("getOrderId", "201405091923");
        map7.put("getCourseName", "Struts2框架应用与开发");
        map7.put("getCoursePrice", "333");
        map7.put("getOrderDate", "2014-05-09");
        HashMap<String, Object> map8 = new HashMap<String, Object>();
        map8.put("getOrderId", "201405190912");
        map8.put("getCourseName", "Hibernate开发实践");
        map8.put("getCoursePrice", "444");
        map8.put("getOrderDate", "2014-05-19");
        HashMap<String, Object> map9 = new HashMap<String, Object>();
        map9.put("getOrderId", "20140523");
        map9.put("getCourseName", "Spring企业应用开发");
        map9.put("getCoursePrice", "555");
        map9.put("getOrderDate", "2014-05-23");
        HashMap<String, Object> map10 = new HashMap<String, Object>();
        map10.put("getOrderId", "201406041209");
        map10.put("getCourseName", "JavaEE轻量级方案SSH");
        map10.put("getCoursePrice", "550");
        map10.put("getOrderDate", "2014-06-04");

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

