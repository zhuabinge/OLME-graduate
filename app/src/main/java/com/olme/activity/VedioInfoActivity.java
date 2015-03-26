package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.olme.R;
import com.olme.adapter.VedioAdapter;
import com.olme.api.CourseApi;
import com.olme.dataSource.VedioInfoData;
import com.olme.domain.RestResult;
import com.olme.popupWindow.MorePopWindow;
import com.olme.application.ExitApplication;
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

import java.util.HashMap;
import java.util.List;

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

    @ViewById(R.id.vedioInfoList)
    ListView listView;

    @RestService
    CourseApi courseApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    private LayoutInflater inflater;
    private View views;
    private VedioAdapter adapter;
    private VedioInfoData vedioInfoData;
    private List<HashMap<String, Object>> list;
    private RestResult vedio = null;
    private Toast toast;
    private boolean vedioResult = false;
    private List<HashMap<String, Object>> dataSource;


    @AfterViews
    void init() {
        //设置ErrorHandler
        courseApi.setRestErrorHandler(errorHandlerForUserService);

        ExitApplication.getInstance().addActivity(this);
        headTitle.setText("视频信息");

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_vedioinfo, null, false);
        Intent intent = getIntent();
        loadData(intent.getIntExtra("courseId", 0));
    }

    public void loadData(int id) {
        final int courseId = id;

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //显示加载进度对话框
                UIHelper.showDialogForLoading(VedioInfoActivity.this, views);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                    //在这里添加调用接口获取数据的代码
                    //doSomething()

                    vedio = courseApi.getTVedioByTcourse(courseId, UrlUtil.getIsAppLogin()); //获取全部课程
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
                    if (vedio.getCode() == 0) {
                        toast = Toast.makeText(VedioInfoActivity.this,
                                vedio.getMsg(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        vedioResult = true;
                    }
                    if (vedioResult) {
                        String data = vedio.getData().toString();
                        String temp = data.substring(1, data.length() - 1);
                        dataSource = ObjectChange.changeVedioInfo(temp);
                        vedioInfoData = new VedioInfoData();
                        vedioInfoData.setData(dataSource);
                        setView();
                    }
                } else {
                    // 加载失败
                    toast = Toast.makeText(VedioInfoActivity.this,
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
        adapter = new VedioAdapter(this);
        adapter.setList(dataSource);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VedioInfoActivity.this, VedioPlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("vedioInfoData", vedioInfoData);
                intent.putExtras(bundle);
                intent.putExtra("position", position);
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
