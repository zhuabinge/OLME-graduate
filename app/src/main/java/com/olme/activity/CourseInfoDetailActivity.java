package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.olme.popupWindow.MorePopWindow;
import com.olme.application.ExitApplication;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Bingo on 2014/8/19.
 * 类别详情
 */

@EActivity(R.layout.activity_courseinfodetail)
public class CourseInfoDetailActivity extends Activity {

    @ViewById(R.id.courseInfoDetailPhoto)
    ImageView courseInfoDetailPhoto;

    @ViewById(R.id.courseInfoDetailName)
    TextView courseInfoDetailName;

    @ViewById(R.id.courseInfoDetailTeacherName)
    TextView courseInfoDetailTeacherName;

    @ViewById(R.id.courseInfoDetailTotalNum)
    TextView courseInfoDetailTotalNum;

    @ViewById(R.id.courseInfoDetailDescription)
    TextView courseInfoDetailDescription;

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.headTitle)
    TextView headTitle;


    private LayoutInflater inflater;
    private View views;


    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);

        headTitle.setText("课程详情");

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_messagedetail, null, false);
        courseInfoDetailPhoto.setImageResource(R.drawable.java_object);
        courseInfoDetailName.setText("Java面向对象开发设计");
        courseInfoDetailTeacherName.setText("肖磊");
        courseInfoDetailTotalNum.setText("20");
        courseInfoDetailDescription.setText("  JAVA语言多年来一直雄踞世界编程语言前三甲，无论是服务器、客户端，还是终端设备，它都无处不在，而且数量庞大。更有450多万开发者从事着JAVA编程的工作，还没有哪种语言能像JAVA那样发展之快，用途之广。");
    }
    @Click(R.id.checkVedio)
    void checkCourseIsClick() {
        Intent intent = new Intent(this, VedioInfoActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.returnbt)
    void returnbtIsClick() {
        CourseInfoDetailActivity.this.finish();  //结束本Activity
    }

    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(CourseInfoDetailActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }
}
