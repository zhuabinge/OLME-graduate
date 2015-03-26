package com.olme.asyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.olme.tool.ImageURI;

import java.io.File;

/**
 * Created by root on 15-3-21.
 */
public final class AsyncImageTask extends AsyncTask<Void, Void, Uri> {

    private ImageView photo;
    private File cache;
    private String path;

    public AsyncImageTask(String path, File cache, ImageView photo) {
        this.path = path;
        this.cache = cache;
        this.photo = photo;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // 后台运行的子线程子线程
    @Override
    protected Uri doInBackground(Void... params) {
        try {
            return ImageURI.getImageURI(this.path, this.cache);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 这个放在在ui线程中执行
    @Override
    protected void onPostExecute(Uri result) {
        super.onPostExecute(result);
        // 完成图片的绑定
        if (result != null) {
            this.photo.setImageURI(result);
        }
    }
}