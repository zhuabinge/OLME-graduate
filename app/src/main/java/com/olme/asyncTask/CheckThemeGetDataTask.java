package com.olme.asyncTask;

import android.os.AsyncTask;
import android.widget.SimpleAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/18.
 */
public class CheckThemeGetDataTask extends AsyncTask<Void, Void, HashMap<String,Object>> {
    private LinkedList<HashMap<String, Object>> list;
    private PullToRefreshListView pullToRefreshListView;
    private SimpleAdapter adapter,adapter1;
    private LinkedList<HashMap<String,Object>> listview;

    public CheckThemeGetDataTask(LinkedList<HashMap<String, Object>> list, LinkedList<HashMap<String,Object>> listview, PullToRefreshListView pullToRefreshListView, SimpleAdapter adapter,SimpleAdapter adapter1) {
        this.list = list;
        this.pullToRefreshListView = pullToRefreshListView;
        this.adapter = adapter;
        this.adapter1 = adapter1;
        this.listview = listview;
    }

    //后台处理部分
    @Override
    protected HashMap<String,Object> doInBackground(Void... params) {
        // Simulates a background job.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("id","teststststststset");

        return map;
    }

    //这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
    //根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
    @Override
    protected void onPostExecute(HashMap<String,Object> result) {
        //在头部增加新添内容
        listview.addFirst(result);
        System.out.println("refresh0");
        System.out.println(list.size());
        adapter1.notifyDataSetChanged();
        //通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
        adapter.notifyDataSetChanged();
        // Call onRefreshComplete when the list has been refreshed.
        pullToRefreshListView.onRefreshComplete();
        System.out.println("refresh1");
        System.out.println(list.size());
        super.onPostExecute(result);
    }
}