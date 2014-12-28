package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.olme.popupWindow.MorePopWindow;
import com.olme.application.ExitApplication;
import com.olme.tool.ResultIntent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Bingo on 2014/8/12.
 * 添加主题
 */
@EActivity(R.layout.activity_addtheme)
public class AddThemeActivity extends Activity{
//    ExitApplication.getInstance().addActivity(this);

    @ViewById(R.id.morebt)
    ImageView morebt;
    private LayoutInflater inflater;
    private View views;
    @ViewById(R.id.themeName)
    EditText themeName;
    @ViewById(R.id.themeContent)
    EditText themeContent;

    @AfterViews
    void init(){
        ExitApplication.getInstance().addActivity(this);

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_messagedetail, null, false);


    }

    @Click(R.id.returnbt)
    void returnbtIsClick() {
        AddThemeActivity.this.finish();  //结束本Activity
    }

    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(AddThemeActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }
    @Click(R.id.replyUser)
    void addTheme(){
        String title = themeName.getText().toString();
        String content = themeContent.getText().toString();
        ResultIntent.resultIntent(this,title,content);
        finish();
    }
}
