package com.olme.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.olme.R;
import com.olme.adapter.MyFragmentAdapter;
import com.olme.application.ExitApplication;
import com.olme.onClickListener.MyOnClickListener;
import com.olme.onPageChangeListener.MyOnPageChangeListener;
import com.olme.popupWindow.MorePopWindow;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 14-12-28.
 */
@EActivity(R.layout.activity_init)
public class InitActivity extends BaseFragmentActivity {

    @ViewById(R.id.viewpage1)
    ViewPager viewpager;

//    @ViewById(R.id.searchbt)
//    ImageView searchbt;

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.classpage)
    TextView course;

    @ViewById(R.id.discusspage)
    TextView discuss;

    @ViewById(R.id.personalpage)
    TextView personal;


    private List<TextView> textviewList;
    MyFragmentAdapter adapters;
    private int currIndex = 0;// 当前页卡编号
    private LayoutInflater inflater;
    private View views;

    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(InitActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }

//    @Click(R.id.searchbt)
//    void searchIsClick(){
//
//    }

    @AfterViews
    void init() {
        initTextView();
        initViewPager();
        ExitApplication.getInstance().addActivity(this);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_main, null, false);
    }

    public void initTextView() {
        textviewList = new ArrayList<TextView>();

        textviewList.add(course);
        textviewList.add(discuss);
        textviewList.add(personal);
        course.setTextColor(android.graphics.Color.parseColor("#55c8d6")); //设置起始背景色
        //监听TextView的点击事件
        course.setOnClickListener(new MyOnClickListener(viewpager, textviewList, 0));
        discuss.setOnClickListener(new MyOnClickListener(viewpager, textviewList, 1));
        personal.setOnClickListener(new MyOnClickListener(viewpager, textviewList, 2));
    }

    public void initViewPager() {
        //适配器
        adapters = new MyFragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapters);
        viewpager.setOnPageChangeListener(new MyOnPageChangeListener(textviewList, currIndex));   //监听ViewPager的变化事件
        adapters.notifyDataSetChanged();
    }
}
