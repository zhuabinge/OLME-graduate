package com.olme.dataSource;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.androidannotations.annotations.EBean;

/**
 * （测试）作为listview中item的数据源
 */

@EBean
public class CourseInfoData implements Serializable{

    private List<HashMap<String,Object>> data;

    public List<HashMap<String, Object>> getData() {
        return data;
    }

    public void setData(List<HashMap<String, Object>> data) {
        this.data = data;
    }
}
