package com.olme.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.olme.activity.ModifyHeadPhotoActivity_;
import com.olme.activity.ModifyNameActivity_;
import com.olme.activity.ModifySexActivity_;
import com.olme.activity.MyQuestionActivity_;
import com.olme.R;
import com.olme.application.CustomApplication;
import com.olme.application.ExitApplication;
import com.olme.asyncTask.AsyncImageTask;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.io.File;

/**
 * Created by Bingo on 2014/8/4.
 * app底部菜单的个人中心
 */
@EFragment(R.layout.activity_personalinfo)
public class PersonalFragment extends Fragment {

    @ViewById(R.id.infoHeadIcon)
    ImageView infoHeadIcon;

    @ViewById(R.id.infoName)
    TextView infoName;

    @ViewById(R.id.infoSex)
    TextView infoSex;

    private LayoutInflater inflater;
    private CustomApplication app;
    private File cache;
    private int userId;

    /**
     * 昵称的requestCode
     */
    private static final int REQUESTCODE_NAME = 1;
    private static final int REQUESTCODE_SEX = 2;
    private static final int REQUESTCODE_HEADPHOTO = 3;

    @AfterViews
    void init() {
        app = (CustomApplication) getActivity().getApplication();
        ExitApplication.getInstance().addActivity(getActivity());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建缓存目录，系统一运行就得创建缓存目录的，
            cache = new File(Environment.getExternalStorageDirectory(), "cache");
            if (!cache.exists()) {
                cache.mkdirs();
            }
        }
        inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userId = app.getUserId();
        //设置TextView显示用户属性
        infoName.setText(app.getUserName());
        infoSex.setText(app.getUserSex());
        String path = UrlUtil.getUrl(app.getUserPhoto());
        AsyncImageTask task = new AsyncImageTask(path, cache, infoHeadIcon);
        task.execute();
        //AsyncLoadImage.loadImage(infoHeadIcon,userPhoto);
    }


    @Click(R.id.myHeadPhoto)
    void myHeadPhotoIsClicked() {
        Intent intent = new Intent(getActivity(), ModifyHeadPhotoActivity_.class);
        Bitmap bitmap = ((BitmapDrawable) infoHeadIcon.getDrawable()).getBitmap();
        intent.putExtra("headPhoto",bitmap);
        startActivityForResult(intent, REQUESTCODE_HEADPHOTO);
    }

    @Click(R.id.myName)
    void myNameIsClicked() {
        Intent intent = new Intent(getActivity(), ModifyNameActivity_.class);
        startActivityForResult(intent, REQUESTCODE_NAME);
    }

    @Click(R.id.mySex)
    void mySexIsClicked() {
        Intent intent = new Intent(getActivity(), ModifySexActivity_.class);
        startActivityForResult(intent, REQUESTCODE_SEX);

    }

    @OnActivityResult(REQUESTCODE_HEADPHOTO)
    void setHeadPhoto(int resultCode, Intent data) {
        //获取头像的bitmap对象
        Bitmap bitmap=data.getParcelableExtra("userPhoto");
        infoHeadIcon.setImageBitmap(bitmap);
    }

    @OnActivityResult(REQUESTCODE_NAME)
    void setName(int resultCode, Intent data) {
        String userName = data.getStringExtra("userName");
        infoName.setText(userName);
    }

    @OnActivityResult(REQUESTCODE_SEX)
    void setSex(int resultCode, Intent data) {
        String userName = data.getStringExtra("userSex");
        infoSex.setText(userName);
    }

    @Click(R.id.myquestion)
    void myQuestion() {
        Intent intent = new Intent(getActivity(), MyQuestionActivity_.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}
