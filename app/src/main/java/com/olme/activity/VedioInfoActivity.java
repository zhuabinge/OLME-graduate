package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.olme.popupWindow.MorePopWindow;
import com.olme.application.ExitApplication;
import com.olme.dataSource.VideoInfoData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/20.
 * 视频信息
 */
@EActivity(R.layout.activity_vedioinfo)
public class VedioInfoActivity extends Activity {


    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.headTitle)
    TextView headTitle;


    private LayoutInflater inflater;
    private View views;
    private SimpleAdapter adapter;
    private VideoInfoData data;
    private LinkedList<HashMap<String, Object>> list;


    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);
        headTitle.setText("视频信息");

        list = data.getDataSource();

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_vedioinfo, null, false);

        adapter = new SimpleAdapter(this, list, R.layout.item_vedioinfo,
                new String[]{"username", "phone"},
                new int[]{R.id.vedioInfoName, R.id.vedioInfoVedioLength});

        final ListView listView = (ListView) this.findViewById(R.id.vedioInfoList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VedioInfoActivity.this, VedioPlayActivity.class);
                startActivity(intent);
            }
        });

    }

    @Click(R.id.returnbt)
    void returnbtIsClick() {
        VedioInfoActivity.this.finish();  //结束本Activity
    }

    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(VedioInfoActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }
}
