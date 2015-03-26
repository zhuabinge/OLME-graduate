package com.olme.tool;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 * Created by root on 15-3-16.
 */
public class UrlUtil {
    public static final String root = "http://192.168.137.1:8080/wxc/";

    public static final String getUrl(String path) {
        return root + path;
    }

    public static final String getIsAppLogin() {
        String str = "";
        int i = 8;
        while(i > 0) {
            str += Math.random()*9;
            i--;
        }
        return MD5Util.MD5(str);
    }

    public static final HttpHeaders getMediaType() {
        MediaType type = new MediaType("application", "json", Charset.forName("UTF-8"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        return headers;
    }
}
