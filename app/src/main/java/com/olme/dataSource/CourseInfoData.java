package com.olme.dataSource;

import java.util.HashMap;
import java.util.LinkedList;
import com.olme.activity.R;
import org.androidannotations.annotations.EBean;

/**
 * （测试）作为listview中item的数据源
 */

@EBean
public class CourseInfoData {
	public static LinkedList<HashMap<String,Object>> getDataSource(){
		LinkedList<HashMap<String,Object>> list = new LinkedList<HashMap<String,Object>>();
		HashMap<String,Object> map1 = new HashMap<String,Object>();
		map1.put("courseInfoPhoto", R.drawable.java_object);
		map1.put("courseInfoName", "Java面向对象开发设计");
		map1.put("courseInfoTeacherName","肖磊");
		map1.put("courseInfoTotalNum","20");
		HashMap<String,Object> map2 = new HashMap<String,Object>();
		map2.put("courseInfoPhoto", R.drawable.oracledatabase);
		map2.put("courseInfoName", "Oracle数据库技术");
		map2.put("courseInfoTeacherName","司徒皓祯");
		map2.put("courseInfoTotalNum","12");
		HashMap<String,Object> map3 = new HashMap<String,Object>();
		map3.put("courseInfoPhoto", R.drawable.javase);
		map3.put("courseInfoName", "JavaSE核心技术");
		map3.put("courseInfoTeacherName","周泽燕");
		map3.put("courseInfoTotalNum","30");
		HashMap<String,Object> map4 = new HashMap<String,Object>();
		map4.put("courseInfoPhoto", R.drawable.web);
		map4.put("courseInfoName", "Web开发-前台设计");
		map4.put("courseInfoTeacherName","宋鸿鹄");
		map4.put("courseInfoTotalNum","234");
		HashMap<String,Object> map5 = new HashMap<String,Object>();
		map5.put("courseInfoPhoto", R.drawable.oraclepl);
		map5.put("courseInfoName", "Oralce PL实践");
		map5.put("courseInfoTeacherName","田旭红");
		map5.put("courseInfoTotalNum","34");
		HashMap<String,Object> map6 = new HashMap<String,Object>();
		map6.put("courseInfoPhoto", R.drawable.javaweb);
		map6.put("courseInfoName", "Java Wev实战");
		map6.put("courseInfoTeacherName","宋鸿志");
		map6.put("courseInfoTotalNum","45");
		HashMap<String,Object> map7 = new HashMap<String,Object>();
		map7.put("courseInfoPhoto", R.drawable.ajax);
		map7.put("courseInfoName", "Ajax架构与实践");
		map7.put("courseInfoTeacherName","肖磊");
		map7.put("courseInfoTotalNum","23");
		HashMap<String,Object> map8 = new HashMap<String,Object>();
		map8.put("courseInfoPhoto", R.drawable.struts2);
		map8.put("courseInfoName", "Struts2框架应用与开发");
		map8.put("courseInfoTeacherName","周泽燕");
		map8.put("courseInfoTotalNum","33");
		HashMap<String,Object> map9 = new HashMap<String,Object>();
		map9.put("courseInfoPhoto", R.drawable.hibernate);
		map9.put("courseInfoName", "Hibernate开发实战");
		map9.put("courseInfoTeacherName","周泽燕");
		map9.put("courseInfoTotalNum","44");
		HashMap<String,Object> map10 = new HashMap<String,Object>();
		map10.put("courseInfoPhoto", R.drawable.spring);
		map10.put("courseInfoName", "Spring企业应用开发");
		map10.put("courseInfoTeacherName","周泽燕");
		map10.put("courseInfoTotalNum","55");
		HashMap<String,Object> map11 = new HashMap<String,Object>();
		map11.put("courseInfoPhoto", R.drawable.javaee);
		map11.put("courseInfoName", "JavaEE轻量级方案SSH");
		map11.put("courseInfoTeacherName","黄杏秀");
		map11.put("courseInfoTotalNum","50");

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
		list.add(map11);
		return list;
	}
}
