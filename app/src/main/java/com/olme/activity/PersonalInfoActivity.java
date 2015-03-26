package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.olme.R;
import com.olme.application.CustomApplication;
import com.olme.asyncTask.AsyncImageTask;
import com.olme.popupWindow.MorePopWindow;
import com.olme.application.ExitApplication;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.io.File;

/**
 * Created by Bingo on 2014/8/10.
 * 个人信息
 */

@EActivity(R.layout.activity_personalinfo)
public class PersonalInfoActivity extends Activity {

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.headTitle)
    TextView headTitle;

    @ViewById(R.id.infoHeadIcon)
    ImageView infoHeadIcon;

    @ViewById(R.id.infoName)
    TextView infoName;

    @ViewById(R.id.infoSex)
    TextView infoSex;

    private LayoutInflater inflater;
    private View views;
    private CustomApplication app;
    private File cache;

    /**
     * 昵称的requestCode
     */
    private static final int REQUESTCODE_NAME = 1;
    private static final int REQUESTCODE_SEX = 2;
    private static final int REQUESTCODE_HEADPHOTO = 3;

    @AfterViews
    void init() {
        app = (CustomApplication) getApplication();
        ExitApplication.getInstance().addActivity(this);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建缓存目录，系统一运行就得创建缓存目录的，
            cache = new File(Environment.getExternalStorageDirectory(), "cache");
            if (!cache.exists()) {
                cache.mkdirs();
            }
        }
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_personalinfo, null, false);
        //设置TextView显示用户属性
        infoName.setText(app.getUserName());
        infoSex.setText(app.getUserSex());
        String path = UrlUtil.getUrl(app.getUserPhoto());
        AsyncImageTask task = new AsyncImageTask(path, cache, infoHeadIcon);
        task.execute();
    }


    @Click(R.id.myHeadPhoto)
    void myHeadPhotoIsClicked() {
        Intent intent = new Intent(this, ModifyHeadPhotoActivity_.class);
        Bitmap bitmap = ((BitmapDrawable) infoHeadIcon.getDrawable()).getBitmap();
        intent.putExtra("headPhoto",bitmap);
        startActivityForResult(intent, PersonalInfoActivity.REQUESTCODE_HEADPHOTO);
    }

//    @Click(R.id.myName)
//    void myNameIsClicked() {
//        Intent intent = new Intent(this, ModifyNameActivity_.class);
//        startActivityForResult(intent, PersonalInfoActivity.REQUESTCODE_NAME);
//    }

    @Click(R.id.mySex)
    void mySexIsClicked() {
        Intent intent = new Intent(this, ModifySexActivity_.class);
        startActivityForResult(intent, PersonalInfoActivity.REQUESTCODE_SEX);
    }

    @OnActivityResult(PersonalInfoActivity.REQUESTCODE_HEADPHOTO)
    void setHeadPhoto(int resultCode, Intent data) {
        //获取头像的bitmap对象
        Bitmap bitmap=data.getParcelableExtra("userPhoto");
        infoHeadIcon.setImageBitmap(bitmap);
    }

//    @OnActivityResult(PersonalInfoActivity.REQUESTCODE_NAME)
//    void setName(int resultCode, Intent data) {
//        String userName = data.getStringExtra("userName");
//        infoName.setText(userName);
//    }

    @OnActivityResult(PersonalInfoActivity.REQUESTCODE_SEX)
    void setSex(int resultCode, Intent data) {
        String userName = data.getStringExtra("userSex");
        infoSex.setText(userName);
    }


    @Click(R.id.myquestion)
    void myQuestion() {
        Intent intent = new Intent(this, MyQuestionActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.returnbt)
        //返回前个页面
    void returnbtIsClicked() {
        headTitle.setText("");
        PersonalInfoActivity.this.finish();  //结束本Activity
    }

    @Click(R.id.morebt)
        //显示下拉菜单
    void morebtIsClicked() {
        MorePopWindow morePopWindow = new MorePopWindow(PersonalInfoActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }
}
