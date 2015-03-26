package com.olme.tool;

import com.olme.domain.CommentResult;
import com.olme.domain.CommunicationResult;
import com.olme.domain.CourseResult;
import com.olme.domain.VedioResult;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bingo on 2014/8/14.
 * 调用ObjMapReflect,将List中的对象转换成HashMap后，再将list返回
 */
public class ObjectChange {
    private static LinkedList<HashMap<String,Object>> list;
    private static HashMap<String,Object> map;

    public static List<HashMap<String,Object>> changeCourse(String temp){
        List<HashMap<String,Object>> listCourse = new ArrayList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();

        String[] array = temp.split("\\}, ");
        for (int i = 0; i < array.length; i++) {
            String str = "";
            if (i < array.length - 1) {
                str = array[i] + "}";
            } else {
                str = array[i];
            }
            CourseResult courseResult1 = JsonUtil.fromObject(str, CourseResult.class);
            map = ObjMapReflect.changeToHashMap(courseResult1);
            listCourse.add(map);
        }
        return listCourse;
    }

    public static List<HashMap<String,Object>> changeVedioInfo( String temp){
        List<HashMap<String,Object>> listVedioInfo = new ArrayList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();

        String[] array = temp.split("\\}, ");
        for (int i = 0; i < array.length; i++) {
            String str = "";
            if (i < array.length - 1) {
                str = array[i] + "}";
            } else {
                str = array[i];
            }
            VedioResult vedioResult = JsonUtil.fromObject(str, VedioResult.class);
            map = ObjMapReflect.changeToHashMap(vedioResult);
            listVedioInfo.add(map);
        }
        return listVedioInfo;
    }

    public static LinkedList<HashMap<String,Object>> changeAllQuestion(String  temp){
        list = new LinkedList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();

        String[] array = temp.split("\\}, ");
        for (int i = 0; i < array.length; i++) {
            String str = "";
            if (i < array.length - 1) {
                str = array[i] + "}";
            } else {
                str = array[i];
            }
            CommunicationResult communicationResult = JsonUtil.fromObject(str, CommunicationResult.class);
            map = ObjMapReflect.changeToHashMap(communicationResult);
            list.add(map);
        }
        return list;
    }

    public static LinkedList<HashMap<String,Object>> changeAllAnswer(String temp){
        list = new LinkedList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();

        String[] array = temp.split("\\}, ");
        for (int i = 0; i < array.length; i++) {
            String str = "";
            if (i < array.length - 1) {
                str = array[i] + "}";
            } else {
                str = array[i];
            }
            CommentResult allQuestionResult = JsonUtil.fromObject(str, CommentResult.class);
            map = ObjMapReflect.changeToHashMap(allQuestionResult);
            list.add(map);
        }
        return list;
    }
}
