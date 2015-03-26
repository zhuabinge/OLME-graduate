package com.olme.dataSource;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wxc on 2014/8/21.
 */
public class VedioInfoData implements Serializable {

    private List<HashMap<String,Object>> data;

    public List<HashMap<String, Object>> getData() {
        return data;
    }

    public void setData(List<HashMap<String, Object>> data) {
        this.data = data;
    }
}
