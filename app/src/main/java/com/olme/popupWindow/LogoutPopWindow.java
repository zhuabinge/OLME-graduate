package com.olme.popupWindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.olme.activity.R;
import com.olme.application.ExitApplication;

/**
 * Created by Bingo on 2014/8/5.
 */
public class LogoutPopWindow {
    private PopupWindow popupWindow;
    private ListView listView;

    public LogoutPopWindow(final Activity context, final View view) {
//        List<String> list = new ArrayList<String>();
//        list.add("退出");
//        list.add("注销");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,list);


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupWindow_view = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_logout_dialog, null, false);

//        listView = (ListView)popupWindow_view.findViewById(R.id.popupListview);
//        listView.setAdapter(adapter);

        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, true);//创建PopupWindow实例
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);



//        listView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if(popupWindow != null &&popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                    System.out.println("menuGridfdsfdsfdfd");
//                }
//                return true;
//            }
//        });

        LinearLayout closeApp = (LinearLayout)popupWindow_view.findViewById(R.id.closeApp);   //dialog.xml视图里面的控件
        LinearLayout logoff = (LinearLayout)popupWindow_view.findViewById(R.id.logoff);
        closeApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitApplication.getInstance().exit();
            }
        });

        logoff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }
        });
    }
}
