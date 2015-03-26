package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.olme.R;
import com.olme.adapter.AnswerDetailAdapter;
import com.olme.api.CommunicationApi;
import com.olme.application.ExitApplication;
import com.olme.dataSource.AllQuestionData;
import com.olme.domain.RestResult;
import com.olme.tool.MyErrorHandler;
import com.olme.tool.ObjectChange;
import com.olme.tool.UIHelper;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/16.
 */
@EActivity(R.layout.activity_answerdetail)
public class AnswerDetailActivity extends Activity {

    @ViewById(R.id.reply_headText)
    TextView reply_headText;

    @ViewById(R.id.answerList)
    PullToRefreshListView pullToRefreshListView;

    @RestService
    CommunicationApi communicationApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    private AnswerDetailAdapter adapter;
    private Toast toast;
    private RestResult answerDetail = null;
    private LinkedList<HashMap<String,Object>> dataSource;
    private LinkedList<HashMap<String,Object>> list;
    private int questionId;
    private LayoutInflater inflater;
    private View views;
    private final int requestCode = 1;


    @AfterViews
    void afterViews() {

        //设置ErrorHandler
        communicationApi.setRestErrorHandler(errorHandlerForUserService);

        ExitApplication.getInstance().addActivity(this);

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.item_answer, null, false);

        list = new LinkedList<HashMap<String, Object>>();
        reply_headText.setText("评论信息");
        Intent intent = getIntent();
        AllQuestionData allQuestionData = (AllQuestionData)intent.getSerializableExtra("allQuestionData");
        //int flag = intent.getIntExtra("flag", 0);
        LinkedList<HashMap<String,Object>> linkedList = allQuestionData.getData();
        int position = intent.getIntExtra("position", 0);
        HashMap<String,Object> map = linkedList.get(position);
        questionId = Integer.parseInt(map.get("questionId").toString());
        asyncQuestionTask(0, 0, false);
    }

    @Background
    void asyncQuestionTask(final int answerId, final int type, final boolean flag) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (!flag) {    //首次加载
                    //显示加载进度对话框
                    UIHelper.showDialogForLoading(AnswerDetailActivity.this, views);
                }
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                    //在这里添加调用接口获取数据的代码
                    //doSomething()

                    /* param[0] 代表问题的id, 0 代表首次获取提问列表
                    ** param[2] 0 代表上拉加载, 1 代表下拉更新
                    */

                    answerDetail = communicationApi.findCommentByQuestionId(questionId, answerId, type, UrlUtil.getIsAppLogin()); //获取全部课程
//                    if (course == null) {
//                        throw new Exception();
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccess) {
                System.out.println("execute()");
                if (isSuccess) {
                    // 加载成功
                    //
                    if (answerDetail.getCode() == 0) {
                        toast = Toast.makeText(AnswerDetailActivity.this,
                                answerDetail.getMsg(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        if (answerDetail.getData() == null) {
                            pullToRefreshListView.onRefreshComplete();
                            return;
                        }
                        String data = answerDetail.getData().toString();
                        String temp = data.substring(1, data.length() - 1);
                        dataSource = ObjectChange.changeAllAnswer(temp);
                        setView(type);
                    }
                } else {
                    // 加载失败
                    toast = Toast.makeText(AnswerDetailActivity.this,
                            "error", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                if (!flag) {
                    //关闭对话框
                    UIHelper.hideDialogForLoading();
                }
            }
        }.execute();
    }

    @UiThread
    void setView(int type) {

        if (list.size() == 0) {
            list = dataSource;
        } else {
            if (type == 0) {    // 代表上拉加载
                for (HashMap<String, Object> map : dataSource){
                    list.addLast(map);
                }
            } else if (type == 1){  //代表下拉更新
                for (int i = dataSource.size() - 1; i >= 0; i-- ){
                    list.addFirst(dataSource.get(i));
                }
            }
        }

        adapter = new AnswerDetailAdapter(AnswerDetailActivity.this);
        adapter.setList(list);

        //设置pull-to-refresh模式为Mode.Both
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        // Set a listener to be invoked when the list should be refreshed.
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                String label = DateUtils.formatDateTime(AnswerDetailActivity. this.getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                int answerId = 0;

                if(refreshView.isHeaderShown()){
                    // Do work to refresh the list here.
                    answerId = Integer.parseInt(list.get(0).get("answerId").toString());
                    System.out.println("---------->answerId: " + answerId);
                    asyncQuestionTask(answerId, 1, true);
                }
                else{
                    answerId = Integer.parseInt(list.get(list.size() - 1).get("answerId").toString());
                    asyncQuestionTask(answerId, 0, true);
                }
            }
        });

        ListView actualListView = pullToRefreshListView.getRefreshableView();
        actualListView.setAdapter(adapter);
        //通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
        adapter.notifyDataSetChanged();
        pullToRefreshListView.onRefreshComplete();
    }

    @Click(R.id.replyUser)
    void replyUserIsClicked() {
        Intent intent = new Intent(this, ReplyActivity_.class);
        intent.putExtra("questionId", questionId);
        startActivityForResult(intent, requestCode);
    }

    @Click(R.id.returnbt)
    void returnbtIsClick() {
        AnswerDetailActivity.this.finish();  //结束本Activity
    }

    @OnActivityResult(requestCode)
    void onActivityResult(int resultCode, Intent data) {

        int answerId = Integer.parseInt(list.get(0).get("answerId").toString());
        asyncQuestionTask(answerId, 1, true);
    }
}
