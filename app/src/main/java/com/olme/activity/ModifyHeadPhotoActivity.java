package com.olme.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.olme.application.ExitApplication;
import com.olme.tool.ResultIntent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
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
    /**
     * 本地数据编辑器
     */
    private SharedPreferences.Editor sharedata;
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
        startActivityForResult(intent, 1);
    }

    /**
     * 选择图片返回处理
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                 this.bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
				/* 将Bitmap设定到ImageView */
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 保存选择的本地图片
     */
    @Click(R.id.photoSave)
    void saveHeadPhoto(){
        if(this.bitmap!=null){
            //TODO 将头像上传到数据库
            //将bitmap作结果返回
            ResultIntent.resultIntent(this, this.bitmap);
        }
        else{
            ResultIntent.resultIntent(this, this.oldBitmap);
        }
        this.finish();//必须手动finish
    }
}
