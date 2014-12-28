package com.olme.asyncTask;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/10.
 * 异步实现listView下拉更新
 */
public class GetDataTask extends AsyncTask<Void, Void, String> {
    private LinkedList<String> mListItems;
    private PullToRefreshListView mPullRefreshListView;
    private ArrayAdapter<String> mAdapter;

    public GetDataTask(LinkedList<String> mListItems, PullToRefreshListView mPullRefreshListView, ArrayAdapter<String> mAdapter) {
        this.mListItems = mListItems;
        this.mPullRefreshListView = mPullRefreshListView;
        this.mAdapter = mAdapter;
    }

    //后台处理部分
    @Override
    protected String doInBackground(Void... params) {
        // Simulates a background job.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        String str = "Added after refresh...I add";
        return str;
    }

    //这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
    //根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
    @Override
    protected void onPostExecute(String result) {
        //在头部增加新添内容
        mListItems.addFirst(result);

        //通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
        mAdapter.notifyDataSetChanged();
        // Call onRefreshComplete when the list has been refreshed.
        mPullRefreshListView.onRefreshComplete();

        super.onPostExecute(result);
    }
}
