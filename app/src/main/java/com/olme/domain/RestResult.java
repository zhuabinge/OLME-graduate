package com.olme.domain;

/**
 * Created by root on 15-3-10.
 */
import java.io.Serializable;

public class RestResult implements Serializable{
    private static final long serialVersionUID = -1809195782514717593L;

    public static final RestResult OK = new RestResult();

    public static final int SUCCESS_CODE = 1;

    public static final String SUCCESS_MSG = "success";

    private int code;

    private String msg;

    private Object data;

    public RestResult() {
        this(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public RestResult(Object data) {
        this(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public RestResult(int code, String msg) {
        this(code, msg, null);
    }

    public RestResult(int code, Object data) {
        this(code, SUCCESS_MSG, data);
    }

    public RestResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
