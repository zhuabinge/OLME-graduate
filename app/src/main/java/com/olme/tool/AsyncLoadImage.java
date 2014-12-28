package com.olme.tool;

import android.widget.ImageView;

import com.olme.asyncTask.ImageLoadTask;

/**
 * Created by Bingo on 2014/8/20.
 */
public class AsyncLoadImage {
    /**
     * 异步加载图片
     *
     * @param imageView 图片容器
     * @param path      图片路径
     */
    public static void loadImage(ImageView imageView, String path) {
        //设置图片容器，异步加载图片
        ImageLoadTask loadImageAsync = new ImageLoadTask(imageView);
        //执行异步加载，传递图片路径
        loadImageAsync.execute(path);
    }
}
