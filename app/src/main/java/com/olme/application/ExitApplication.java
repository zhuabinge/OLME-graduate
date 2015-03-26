package com.olme.application;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bingo on 2014/8/10.
 * 完全退出应用程序
 */
public class ExitApplication extends Application {
    private List<Activity> activitys = null;
    private static ExitApplication instance;

    private ExitApplication() {
        activitys = new LinkedList<Activity>();
    }

    /**
     * 单例模式中获取唯一的MyApplication实例
     *
     * @return
     */
    public static ExitApplication getInstance() {
        if (null == instance) {
            instance = new ExitApplication();
        }
        return instance;

    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        } else {
            activitys.add(activity);
        }

    }

    // 遍历所有Activity并finish
    public void exit() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
        File cache = new File(Environment.getExternalStorageDirectory(), "cache");
        //清空缓存
        File[] files = cache.listFiles();
        for(File file :files){
            file.delete();
        }
        System.exit(0);
    }
}
