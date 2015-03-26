package com.olme.tool;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.PopupWindow;

import com.olme.R;

/**
 * Created by root on 15-3-13.
 */
public class UIHelper {

    private static PopupWindow popupWindow;

    public static void showDialogForLoading(final Activity context, final View view) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupWindow_view = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_progressbar, null, false);

        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, true);//创建PopupWindow实例
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public static void hideDialogForLoading() {
        popupWindow.dismiss();
    }
}
