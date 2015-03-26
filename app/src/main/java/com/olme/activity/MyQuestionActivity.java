package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.olme.R;
import com.olme.adapter.CommunicationAdapter;
import com.olme.api.CommunicationApi;
import com.olme.application.CustomApplication;
import com.olme.domain.RestResult;
import com.olme.popupWindow.MorePopWindow;
import com.olme.application.ExitApplication;
import com.olme.dataSource.MyQuestionData;
import com.olme.tool.MyErrorHandler;
import com.olme.tool.ObjectChange;
import com.olme.tool.UIHelper;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/9.
 * 我发布过的主题
 */
@EActivity(R.layout.activity_myquestion)
public class MyQuestionActivity extends Activity {

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.headTitle)
    TextView headTitle;

    @ViewById(R.id.myQuestionList)
    PullToRefreshListView pullToRefreshListView;

    @RestService
    CommunicationApi communicationApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    private MyQuestionData myQuestionData;
    private LayoutInflater inflater;
    private View view;
    private CommunicationAdapter adapter;
    private Toast toast;
    private RestResult communication = null;
    private LinkedList<HashMap<String,Object>> dataSource;
    private LinkedList<HashMap<String,Object>> list;
    private File cache;
    private final int requestCode = 1;
    private CustomApplication app;
    private int userId;


    @AfterViews
    void init() {
        //设置ErrorHandler
        communicationApi.setRestErrorHandler(errorHandlerForUserService);
        ExitApplication.getInstance().addActivity(this);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建缓存目录，系统一运行就得创建缓存目录的，
            cache = new File(Environment.getExternalStorageDirectory(), "cache");
            if (!cache.exists()) {
                cache.mkdirs();
            }
        }
        app = (CustomApplication) getApplication();;
        userId = app.getUserId();
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_myquestion, null, false);
        headTitle.setText("我的提问");  //设置标题
        asyncQuestionTask(0, false);
    }



    public void asyncQuestionTask(final int questionId,final boolean flag) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (!flag) {    //首次加载
                    //显示加载进度对话框
                    UIHelper.showDialogForLoading(MyQuestionActivity.this, view);
                }
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                    //在这里添加调用接口获取数据的代码
                    //doSomething()

                    /* param[0] 代表问题的id, 0 代表首次获取提问列表
                    ** param[1] 0 代表上拉加载, 1 代表下拉更新
                    */

                    communication = communicationApi.getUserCommunication(userId, questionId, UrlUtil.getIsAppLogin()); //获取全部课程
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
                    if (communication.getCode() == 0) {
                        toast = Toast.makeText(MyQuestionActivity.this,
                                communication.getMsg(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        if (communication.getData() == null) {
                            pullToRefreshListView.onRefreshComplete();
                            return;
                        }
                        String data = communication.getData().toString();
                        String temp = data.substring(1, data.length() - 1);
                        dataSource = ObjectChange.changeAllQuestion(temp);
                        setView();
                    }
                } else {
                    // 加载失败
                    toast = Toast.makeText(MyQuestionActivity.this,
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
    void setView() {

        if (list.size() == 0) {
            list = dataSource;
        } else {
            for (HashMap<String, Object> map : dataSource){
                list.addLast(map);
            }
        }

        myQuestionData = new MyQuestionData();
        myQuestionData.setData(list);

        adapter = new CommunicationAdapter(MyQuestionActivity.this, cache);
        adapter.setList(list);

        //设置pull-to-refresh模式为Mode.Both
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        // Set a listener to be invoked when the list should be refreshed.
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                String label = DateUtils.formatDateTime(MyQuestionActivity.this.getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                int themeId = Integer.parseInt(list.get(list.size() - 1).get("questionId").toString());
                asyncQuestionTask(themeId, true);
            }
        });

        ListView actualListView = pullToRefreshListView.getRefreshableView();
        actualListView.setAdapter(adapter);
        //通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
        adapter.notifyDataSetChanged();
        pullToRefreshListView.onRefreshComplete();

        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),AnswerDetailActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("allQuestionData", myQuestionData);
                intent.putExtras(bundle);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }


    @Click(R.id.returnbt)
    void returnbtIsClick() {
        MyQuestionActivity.this.finish();  //结束本Activity
    }


    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(MyQuestionActivity.this, view);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }
}
