package com.olme.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.olme.R;
import com.olme.activity.AddThemeActivity_;
import com.olme.activity.AnswerDetailActivity_;
import com.olme.adapter.CommunicationAdapter;
import com.olme.api.CommunicationApi;
import com.olme.application.CustomApplication;
import com.olme.application.ExitApplication;
import com.olme.dataSource.AllQuestionData;
import com.olme.domain.RestResult;
import com.olme.tool.MyErrorHandler;
import com.olme.tool.ObjectChange;
import com.olme.tool.UIHelper;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by root on 15-3-22.
 */

@EFragment(R.layout.tab2)
public class CommunicationFragment extends BaseFragment{
    private View mFragmentView;
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;
    private CommunicationAdapter adapter;
    private Toast toast;
    private RestResult communication = null;
    private LinkedList<HashMap<String,Object>> dataSource;
    private LinkedList<HashMap<String,Object>> list;
    private File cache;
    private AllQuestionData allQuestionData;
    private final int requestCode = 1;
    private CustomApplication app;

    @ViewById(R.id.themeList)
    PullToRefreshListView pullToRefreshListView;

    @RestService
    CommunicationApi communicationApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;


    @AfterViews
    void afterViews() {

        //设置ErrorHandler
        communicationApi.setRestErrorHandler(errorHandlerForUserService);
        ExitApplication.getInstance().addActivity(getActivity());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建缓存目录，系统一运行就得创建缓存目录的，
            cache = new File(Environment.getExternalStorageDirectory(), "cache");
            if (!cache.exists()) {
                cache.mkdirs();
            }
        }
        app = (CustomApplication) getActivity().getApplication();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(R.layout.tab2, container, false);
            list = new LinkedList<HashMap<String, Object>>();
            isPrepared = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    lazyLoad();
                }
            }, 100);
        }

        //因为共用一个Fragment视图，所以当前这个视图已被加载到Activity中，必须先清除后再加入Activity
        ViewGroup parent = (ViewGroup) mFragmentView.getParent();
        if (parent != null) {
            parent.removeView(mFragmentView);
        }

        return mFragmentView;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        asyncQuestionTask(0, 0, false);
    }

    public void asyncQuestionTask(final int id,final int type,final boolean flag) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (!flag) {    //首次加载
                    //显示加载进度对话框
                    UIHelper.showDialogForLoading(getActivity(), mFragmentView);
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

                    communication = communicationApi.getAllCommunication(id, type, UrlUtil.getIsAppLogin()); //获取全部课程
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
                        toast = Toast.makeText(getActivity(),
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
                        setView(type);
                    }
                    mHasLoadedOnce = true;
                } else {
                    // 加载失败
                    toast = Toast.makeText(getActivity(),
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

        allQuestionData = new AllQuestionData();
        allQuestionData.setData(list);

        adapter = new CommunicationAdapter(getActivity(), cache);
        adapter.setList(list);

        //设置pull-to-refresh模式为Mode.Both
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        // Set a listener to be invoked when the list should be refreshed.
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                int themeId = 0;

                if(refreshView.isHeaderShown()){
                    // Do work to refresh the list here.
                    themeId = Integer.parseInt(list.get(0).get("questionId").toString());
                    System.out.println("---------->themeId: " + themeId);
                    asyncQuestionTask(themeId, 1, true);
                }
                else{
                    themeId = Integer.parseInt(list.get(list.size() - 1).get("questionId").toString());
                    asyncQuestionTask(themeId, 0, true);
                }
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
                bundle.putSerializable("allQuestionData", allQuestionData);
                intent.putExtras(bundle);
                intent.putExtra("position",position);
                //intent.putExtra("flag",0);
                startActivity(intent);
            }
        });
    }

    @Click(R.id.writePanel)
    void addQuestion() {
        Intent intent = new Intent(getActivity(), AddThemeActivity_.class);

        int userId = app.getUserId();
        intent.putExtra("userId", userId);
        startActivityForResult(intent, requestCode);
    }

    @OnActivityResult(requestCode)
    void onActivityResult(int resultCode, Intent data) {
        int themeId = Integer.parseInt(list.get(0).get("questionId").toString());
        asyncQuestionTask(themeId, 1, true);
    }

    //@Click(R.id.messageCount)
}
