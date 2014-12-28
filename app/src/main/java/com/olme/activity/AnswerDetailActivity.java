package com.olme.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.olme.application.ExitApplication;
import com.olme.asyncTask.AnswerGetDataTask;
import com.olme.asyncTask.QuestionGetDatTask;
import com.olme.dataSource.AnswerData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/16.
 */
@EActivity(R.layout.activity_answerdetail)
public class AnswerDetailActivity extends Activity {

    @ViewById(R.id.reply_headText)
    TextView reply_headText;

    private PullToRefreshListView pullToRefreshListView;
    private SimpleAdapter adapter;
    private AnswerData data;
    private LinkedList<HashMap<String, Object>> list;

    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);
        reply_headText.setText("评论信息");
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 1);
        int flag = intent.getIntExtra("flag", 0);
        if (flag == 0) {
            list = data.getAnswerDataSource(position);
        } else {
            list = data.getMyMessageDataSource(position);
        }
        pullToRefreshListView = (PullToRefreshListView) this.findViewById(R.id.answerList);

        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.isHeaderShown()) {
                    String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                            DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                    // Update the LastUpdatedLabel
                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                    // Do work to refresh the list here.
                    new AnswerGetDataTask(list, pullToRefreshListView, adapter, true).execute();
                } else {
                    new AnswerGetDataTask(list, pullToRefreshListView, adapter, false).execute();
                }
            }
        });

        adapter = new SimpleAdapter(this, list, R.layout.item_answer,
                new String[]{"vedioLength", "name", "rating"},
                new int[]{ R.id.answerUserName, R.id.answerDate, R.id.answerContent});

        ListView actualListView = pullToRefreshListView.getRefreshableView();
        actualListView.setAdapter(adapter);


        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ReplyActivity_.class);
                startActivity(intent);
            }
        });
    }

    @Click(R.id.replyUser)
    void replyUserIsClicked() {
        Intent intent = new Intent(this, ReplyActivity_.class);
        startActivityForResult(intent, 1);
        //startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 9) {
            String editTextValue = data.getStringExtra("editTextValue");
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", "2014-08-21");
            map.put("vedioLength", "Jordan");
            map.put("rating", editTextValue);
            list.addLast(map);
            //通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
            adapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            pullToRefreshListView.onRefreshComplete();
        }
    }

    @Click(R.id.returnbt)
    void returnbtIsClick() {
        AnswerDetailActivity.this.finish();  //结束本Activity
    }

}
