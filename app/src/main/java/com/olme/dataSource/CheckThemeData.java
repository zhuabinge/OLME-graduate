package com.olme.dataSource;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/18.
 */
public class CheckThemeData {

    public static LinkedList<HashMap<String, Object>> getDataSource() {
        ListViewData listviewData = new ListViewData();
        LinkedList<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("list", listviewData.getDataSource());
        list.add(map1);
        return list;
    }
}
