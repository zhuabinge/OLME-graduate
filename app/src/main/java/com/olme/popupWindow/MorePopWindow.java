package com.olme.popupWindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.olme.activity.LogoutActivity_;
import com.olme.R;
import com.olme.application.CustomApplication;
import com.olme.dataSource.popupDataSource_1;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Bingo on 2014/8/4.
 * app右上角的更多菜单窗口
 */
public class MorePopWindow extends PopupWindow {
    private View conentView;
    private Button btn_cancel;
    private SimpleAdapter adapter;
    popupDataSource_1 data;
    private CustomApplication app;

    public MorePopWindow(final Activity context, final View views) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.more_popup_dialog1, null);
        ListView listView1 = (ListView) conentView.findViewById(R.id.popupListview);
        List<HashMap<String, Object>> map;
        app = (CustomApplication) context.getApplication();
        System.out.println("-----> more  " + app.getValue());
       if ("Login".equals(app.getValue())) {
            map = data.getData_1();
        } else {
            map = data.getData_2();
        }
        adapter = new SimpleAdapter(context, map, R.layout.popup_item1,
                new String[]{"popupimage", "popuptext"},
                new int[]{R.id.popupimage, R.id.popuptext});
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
//                        if (!"Login".equals(app.getValue())) {
//                            dismiss();
//                            Intent intent = new Intent(context, LoginActivity_.class);
//                            context.startActivity(intent);
//                        }
                        break;
                    case 1:break;
                    case 2:
                        dismiss();
                        Intent intent = new Intent(context, LogoutActivity_.class);
                        context.startActivity(intent);
//                        LogoutDialog logoutDialog = new LogoutDialog(context,R.style.MyDialog);
//                        logoutDialog.show();
                        //new LogoutPopWindow(context, views);
                        break;
                    default:
                        break;
                }

            }
        });

        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 2 + 50);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);

    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
            //this.sh
        } else {
            this.dismiss();
        }
    }
}
