package com.olme.tool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

/**
 * Created by ye on 2014/8/16.
 * 为startActivityForResult提供返回数据
 */
public class ResultIntent {
    /**
     * 设置返回的数据
     * @param activity 数据返回的界面
     * @param editTextValue 传输信息
     */
    public static void  resultIntent(Activity activity,String editTextValue){
        Intent intent = new Intent();
        intent.putExtra("editTextValue", editTextValue);
        activity.setResult(9, intent);
    }
    public static void  resultIntent(Activity activity,Bitmap bitmap){
        Intent intent = new Intent();
        intent.putExtra("headPhoto", bitmap);
        activity.setResult(9, intent);
    }
   /**
    * 添加主题返回使用的Intent,返回主题名和主题内容
    * */
    public static void resultIntent(Activity activity,String title,String content){
        Intent intent = new Intent();
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        activity.setResult(9, intent);
    }
}
