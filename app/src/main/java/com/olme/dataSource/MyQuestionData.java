package com.olme.dataSource;

import com.olme.activity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bingo on 2014/8/16.
 */
public class MyQuestionData {
    public static LinkedList<HashMap<String, Object>> getDataSource() {
        LinkedList<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("id","199101");
        map.put("img", R.drawable.defalt_head);
        map.put("username", "Jordan");
        map.put("imagecontent",R.drawable.face);
        map.put("date", "2014-7-29");
        map.put("phone", "软件实现规约怎么写");
        map.put("address", "求一份实现规约的模板！");
        map.put("count", "3");
        list.addLast(map);
        map = new HashMap<String, Object>();
        map.put("id","199101");
        map.put("imagecontent",R.drawable.face);
        map.put("img", R.drawable.defalt_head);
        map.put("username", "Jordan");
        map.put("date", "2014-7-29");
        map.put("phone", "PowerDesigner设计的时候同名对应到同一个属性");
        map.put("address", "怎么办，改了一个另一个也跟着改，数据库比较大，删除工作量太大了！");
        map.put("count", "2");
        list.addLast(map);
         map = new HashMap<String, Object>();
        map.put("id","199101");
        map.put("img", R.drawable.defalt_head);
        map.put("username", "Jordan");
        map.put("date", "2014-7-29");
        map.put("imagecontent",R.drawable.face);
        map.put("phone", "软件开发路线怎么写");
        map.put("address", "头疼啊，没模板很难弄");
        map.put("count", "1");
        list.add(map);

        return list;
    }
}