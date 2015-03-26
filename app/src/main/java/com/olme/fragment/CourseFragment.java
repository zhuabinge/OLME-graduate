package com.olme.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.olme.activity.CourseInfoDetailActivity_;
import com.olme.R;
import com.olme.adapter.CourseAdapter;
import com.olme.api.CourseApi;
import com.olme.application.ExitApplication;
import com.olme.dataSource.CourseInfoData;
import com.olme.domain.RestResult;
import com.olme.tool.MyErrorHandler;
import com.olme.tool.ObjectChange;
import com.olme.tool.UIHelper;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 15-3-13.
 */

@EFragment(R.layout.tab1)
public class CourseFragment extends BaseFragment {

    private View mFragmentView;
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;
    private CourseAdapter adapter;
    private Toast toast;
    private RestResult course = null;
    private List<HashMap<String, Object>> dataSource;
    private File cache;
    private CourseInfoData courseInfoData;

    @ViewById(R.id.courseInfoList)
    ListView listView1;

    @RestService
    CourseApi courseApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    @AfterViews
    void afterViews() {
        ExitApplication.getInstance().addActivity(getActivity());
        //设置ErrorHandler
        courseApi.setRestErrorHandler(errorHandlerForUserService);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建缓存目录，系统一运行就得创建缓存目录的，
            cache = new File(Environment.getExternalStorageDirectory(), "cache");
            if (!cache.exists()) {
                cache.mkdirs();
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(R.layout.tab1, container, false);
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

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //显示加载进度对话框
                UIHelper.showDialogForLoading(getActivity(), mFragmentView);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                    //在这里添加调用接口获取数据的代码
                    //doSomething()

                    course = courseApi.getAllTcourse(UrlUtil.getIsAppLogin()); //获取全部课程
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
                    if (course.getCode() == 0) {
                        toast = Toast.makeText(getActivity(),
                                course.getMsg(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        String data = course.getData().toString();
                        String temp = data.substring(1, data.length() - 1);
                        dataSource = ObjectChange.changeCourse(temp);
                        courseInfoData = new CourseInfoData();
                        courseInfoData.setData(dataSource);
                        setView();
                    }
                    mHasLoadedOnce = true;
                } else {
                    // 加载失败
                    toast = Toast.makeText(getActivity(),
                            "error", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                //关闭对话框
                UIHelper.hideDialogForLoading();
            }
        }.execute();
    }

    @UiThread
    void setView() {
        adapter = new CourseAdapter(getActivity(), cache);
        adapter.setList(dataSource);
        listView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CourseInfoDetailActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("courseInfoData", courseInfoData);
                intent.putExtras(bundle);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}
