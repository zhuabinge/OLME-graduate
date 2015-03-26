package com.olme.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.olme.R;
import com.olme.application.ExitApplication;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.io.FileNotFoundException;

/**
 * Created by Bingo on 2014/8/14.
 * 修改我的头像
 */
@EActivity(R.layout.activity_modifyheadphoto)
public class ModifyHeadPhotoActivity extends Activity {
    @ViewById(R.id.modifyName)
    ImageView imageView;

    private final int requestCode = 1;
    Bitmap bitmap;
    Bitmap oldBitmap;

    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);
        Intent intent = getIntent();
        this.oldBitmap = (Bitmap)intent.getParcelableExtra("headPhoto");
        imageView.setImageBitmap(oldBitmap);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //设置窗口的大小及透明度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        this.setFinishOnTouchOutside(true);
    }

    /**
     * 选择本机图片
     */
    @Click(R.id.photoSelect)
    void selectLocalImage(){
        Intent intent = new Intent();
			               /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
			               /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
			               /* 取得相片后返回本画面 */
        startActivityForResult(intent, requestCode);
    }

    /**
     * 选择图片返回处理
     * @param resultCode
     * @param data
     */
    @OnActivityResult(requestCode)
    void onActivityResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            //Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                 this.bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
				/* 将Bitmap设定到ImageView */
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
    }

    /**
     * 保存选择的本地图片
     */
    @Click(R.id.photoSave)
    void saveHeadPhoto(){

        //上传到服务器

        this.finish();//必须手动finish
    }
}
