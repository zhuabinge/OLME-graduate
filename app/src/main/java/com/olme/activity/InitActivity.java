package com.olme.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.olme.adapter.MyFragmentAdapter;
import com.olme.application.CustomApplication;
import com.olme.application.ExitApplication;
import com.olme.fragment.CommunicationFragment;
import com.olme.fragment.CourseFragment;
import com.olme.fragment.PersonalFragment;
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
public class InitActivity extends FragmentActivity {

    @ViewById(R.id.viewpage1)
    ViewPager viewpager;

    @ViewById(R.id.searchbt)
    ImageView searchbt;

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.classpage)
    TextView course;

    @ViewById(R.id.discusspage)
    TextView discuss;

    @ViewById(R.id.personalpage)
    TextView personal;

    @ViewById(R.id.et_username)
    EditText etUsername;

    @ViewById(R.id.et_password)
    EditText etPassword;

    @ViewById(R.id.checkboxpw)
    CheckBox cbpassword;

    @ViewById(R.id.checkboxlg)
    CheckBox cblogin;

    private List<TextView> textviewList;
    private MyFragmentAdapter adapters;
    private int currIndex = 0;// 当前页卡编号
    private FragmentManager manager;
    private List<Fragment> list = null;
    private LayoutInflater inflater;
    private View views;
    private SharedPreferences sp;
    private CustomApplication app;

    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(InitActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }

    @Click(R.id.searchbt)
    void searchIsClick(){

    }


    @AfterViews
    void init() {
        initTextView();
        initViewPager();
        initLogin();
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
        manager = getSupportFragmentManager();
        list = new ArrayList<Fragment>();
        list.add(new CourseFragment());
        list.add(new CommunicationFragment());
        list.add(new PersonalFragment());
        // transaction = manager.beginTransaction();
        adapters = new MyFragmentAdapter(manager, list);
        viewpager.setAdapter(adapters);
        viewpager.setOnPageChangeListener(new MyOnPageChangeListener(list,textviewList, currIndex));   //监听ViewPager的变化事件
        adapters.notifyDataSetChanged();
    }

    public void initLogin(){
        app = (CustomApplication) getApplication();
        sp = this.getSharedPreferences("userInfo", 0);
        System.out.println("-------> " + sp.getBoolean("ISCHECK", false));
        System.out.println("-------> " + app.getValue());
        if(sp.getBoolean("ISCHECK", false))
        {
            //判断自动登陆多选框状态
            if(sp.getBoolean("AUTO_ISCHECK", false))
            {
                app.setValue("Login");  //设置登陆状态
                System.out.println("-------> " + app.getValue());
                //跳转界面
//                Intent intent = new Intent(LoginActivity.this,LogoActivity.class);
//                LoginActivity.this.startActivity(intent);

            }
        }
    }
}
