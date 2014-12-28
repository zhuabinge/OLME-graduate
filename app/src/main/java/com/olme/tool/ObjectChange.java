package com.olme.tool;

import com.olme.domain.AllAnswer;
import com.olme.domain.AllQuestion;
import com.olme.domain.CourseInfo;
import com.olme.domain.CourseType;
import com.olme.domain.MyQuestion;
import com.olme.domain.QuestionPhoto;

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

    public static LinkedList<HashMap<String,Object>> changeCourseType( List<CourseType> courseTypeList){
        list = new LinkedList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        for(int i = 0; i < courseTypeList.size(); i++){
            map = ObjMapReflect.changeToHashMap(courseTypeList.get(i));
            list.add(map);
        }
        return list;
    }

    public static LinkedList<HashMap<String,Object>> changeCourseInfo( List<CourseInfo> courseInfoList){
        list = new LinkedList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        for(int i = 0; i < courseInfoList.size(); i++){
            map = ObjMapReflect.changeToHashMap(courseInfoList.get(i));
            list.add(map);
        }
        return list;
    }

    public static LinkedList<HashMap<String,Object>> changeAllQuestion( List<AllQuestion> allQuestionList){
        list = new LinkedList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        for(int i = 0; i < allQuestionList.size(); i++){
            map = ObjMapReflect.changeToHashMap(allQuestionList.get(i));
            list.add(map);
        }
        return list;
    }

    public static LinkedList<HashMap<String,Object>> changeAllAnswer( List<AllAnswer> allAnswerList){
        list = new LinkedList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        for(int i = 0; i < allAnswerList.size(); i++){
            map = ObjMapReflect.changeToHashMap(allAnswerList.get(i));
            list.add(map);
        }
        return list;
    }

    public static LinkedList<HashMap<String,Object>> changeMyQuestion( List<MyQuestion> myQuestionList){
        list = new LinkedList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        for(int i = 0; i < myQuestionList.size(); i++){
            map = ObjMapReflect.changeToHashMap(myQuestionList.get(i));
            list.add(map);
        }
        return list;
    }

    public static LinkedList<HashMap<String,Object>> changeQuestionPhoto( List<QuestionPhoto> questionPhotoList){
        list = new LinkedList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        for(int i = 0; i < questionPhotoList.size(); i++){
            map = ObjMapReflect.changeToHashMap(questionPhotoList.get(i));
            list.add(map);
        }
        return list;
    }
}
