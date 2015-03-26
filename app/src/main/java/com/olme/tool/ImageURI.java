package com.olme.tool;

import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by root on 15-3-21.
 */
public class ImageURI {

    /*
 * 从网络上获取图片，如果图片在本地存在的话就直接拿，如果不存在再去服务器上下载图片
 * 这里的path是图片的地址
 */
    public static Uri getImageURI(String path, File cache) {
        String name = MD5Util.MD5(path) + path.substring(path.lastIndexOf("."));
        final File file = new File(cache, name);
        final String root = path;
        // 如果图片存在本地缓存目录，则不去服务器下载
        if (file.exists()) {
            return Uri.fromFile(file);//Uri.fromFile(path)这个方法能得到文件的URI
        } else {
            try {
                // 从网络上获取图片
                URL url = new URL(root);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000); // 注意要设置超时，设置时间不要超过10秒，避免被android系统回收
                conn.setRequestMethod("GET");
                if (conn.getResponseCode() != 200)
                    throw new RuntimeException("请求url失败");
                //conn.setDoInput(true);
                InputStream is = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 返回一个URI对象
            return Uri.fromFile(file);
        }
    }
}
