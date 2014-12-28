package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.olme.popupWindow.MorePopWindow;
import com.olme.application.ExitApplication;
import com.olme.asyncTask.AnswerGetDataTask;
import com.olme.dataSource.AnswerData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/18.
 */
@EActivity(R.layout.activity_messagedetail)
public class MessageDetailActivity extends Activity {

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.headTitle)
    TextView headTitle;

    private PullToRefreshListView pullToRefreshListView;
    private SimpleAdapter adapter;
    private AnswerData data;
    private LinkedList<HashMap<String,Object>> list;
    private LayoutInflater inflater;
    private View views;


    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_messagedetail, null, false);
        headTitle.setText("我的消息");
        list = data.getMyAllDataSource();
        pullToRefreshListView = (PullToRefreshListView) this.findViewById(R.id.messageDetailList);  //下拉刷新
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                // Do work to refresh the list here.
                new AnswerGetDataTask(list, pullToRefreshListView, adapter,false).execute();
            }
        });

        adapter = new SimpleAdapter(this, list, R.layout.item_answer,
                new String[]{"name", "vedioLength", "rating"},
                new int[]{ R.id.answerUserName, R.id.answerDate, R.id.answerContent});

        ListView actualListView = pullToRefreshListView.getRefreshableView();
        actualListView.setAdapter(adapter);


        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), SelectReplyActivity_.class);
                startActivity(intent);
            }
        });
    }


    @Click(R.id.returnbt)
    void returnbtIsClick() {
        MessageDetailActivity.this.finish();  //结束本Activity
    }


    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(MessageDetailActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }
}
