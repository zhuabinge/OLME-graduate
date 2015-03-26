package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.olme.R;
import com.olme.asyncTask.AsyncImageTask;
import com.olme.dataSource.CourseInfoData;
import com.olme.popupWindow.MorePopWindow;
import com.olme.application.ExitApplication;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bingo on 2014/8/19.
 * 类别详情
 */

@EActivity(R.layout.activity_courseinfodetail)
public class CourseInfoDetailActivity extends Activity {

    @ViewById(R.id.courseInfoDetailPhoto)
    ImageView courseInfoDetailPhoto;

    @ViewById(R.id.courseInfoDetailName)
    TextView courseInfoDetailName;

    @ViewById(R.id.courseInfoDetailTotalNum)
    TextView courseInfoDetailTotalNum;

    @ViewById(R.id.courseInfoDetailDescription)
    TextView courseInfoDetailDescription;

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.headTitle)
    TextView headTitle;

    private LayoutInflater inflater;
    private View views;
    private File cache;
    private int courseId;

    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建缓存目录，系统一运行就得创建缓存目录的，
            cache = new File(Environment.getExternalStorageDirectory(), "cache");
            if (!cache.exists()) {
                cache.mkdirs();
            }
        }

        headTitle.setText("课程详情");
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_courseinfodetail, null, false);

        Intent intent = getIntent();
        CourseInfoData courseInfoData = (CourseInfoData)intent.getSerializableExtra("courseInfoData");
        List<HashMap<String,Object>> list = courseInfoData.getData();
        int position = intent.getIntExtra("position", 0);
        HashMap<String,Object> map = list.get(position);

        courseId = Integer.parseInt(map.get("courseId").toString());
        courseInfoDetailName.setText(map.get("courseName").toString());
        //courseInfoDetailTotalNum.setText(map.get("courseCount").toString());
        courseInfoDetailDescription.setText(map.get("courseDescription").toString());

        String path = UrlUtil.getUrl(map.get("coursePhoto").toString());
        AsyncImageTask task = new AsyncImageTask(path, cache, courseInfoDetailPhoto);
        task.execute();
    }

    @Click(R.id.checkVedio)
    void checkCourseIsClick() {
        Intent intent = new Intent(this, VedioInfoActivity_.class);
        intent.putExtra("courseId", courseId);
        startActivity(intent);
    }

    @Click(R.id.returnbt)
    void returnbtIsClick() {
        CourseInfoDetailActivity.this.finish();  //结束本Activity
    }

    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(CourseInfoDetailActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }
}
