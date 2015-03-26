package com.olme.dataSource;


import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/17.
 */
public class AllQuestionData implements Serializable {

    private LinkedList<HashMap<String,Object>> data;

    public LinkedList<HashMap<String, Object>> getData() {
        return data;
    }

    public void setData(LinkedList<HashMap<String, Object>> data) {
        this.data = data;
    }
}
