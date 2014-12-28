package com.olme.popupWindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.olme.activity.InitActivity_;
import com.olme.activity.R;

/**
 * Created by Bingo on 2014/8/9.
 */
public class LoginTipPopWindow{
    private PopupWindow popupWindow;

    public LoginTipPopWindow(final Activity context, final View view) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View popupWindow_view = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.logintip_popup_dialog, null, false);
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);//创建PopupWindow实例
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        view.setFocusable(true);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button button = (Button) popupWindow_view.findViewById(R.id.tologin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, InitActivity_.class);
                context.startActivity(intent);
            }
        });
    }
}
