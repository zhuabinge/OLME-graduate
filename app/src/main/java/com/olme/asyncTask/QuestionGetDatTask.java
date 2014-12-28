package com.olme.asyncTask;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.SimpleAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.olme.activity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bingo on 2014/8/17.
 */
public class QuestionGetDatTask extends AsyncTask<Void, Void, HashMap<String, Object>> {
    private LinkedList<HashMap<String, Object>> list;
    private PullToRefreshListView pullToRefreshListView;
    private SimpleAdapter adapter;
    private boolean isHead;


    public QuestionGetDatTask(LinkedList<HashMap<String, Object>> list, PullToRefreshListView pullToRefreshListView, SimpleAdapter adapter,boolean isHead) {
        this.list = list;
        this.pullToRefreshListView = pullToRefreshListView;
        this.adapter = adapter;
        this.isHead = isHead;
    }


    //后台处理部分
    @Override
    protected HashMap<String, Object> doInBackground(Void... params) {
        // Simulates a background job.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id","199101");
        map.put("img", R.drawable.defalt_head);
        map.put("username", "Jordan");
        map.put("date", "2014-7-29");
        map.put("imagecontent",R.drawable.face);
        map.put("phone", "软件开发路线怎么写");
        map.put("address", "头疼啊，没模板很难弄");
        map.put("count", "1");

        return map;
    }

    //这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
    //根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
    @Override
    protected void onPostExecute(HashMap<String, Object> result) {
        //在尾部增加新添内容

            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1 = new HashMap<String, Object>();
            map1.put("id","199101");
            map1.put("img", R.drawable.defalt_head);
            map1.put("username", "Bosh");
            map1.put("imagecontent",R.drawable.face);
            map1.put("date", "2014-7-29");
            map1.put("phone", "软件实现规约怎么写");
            map1.put("address", "求一份实现规约的模板！");
            map1.put("count", "3");

        HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id","199101");
            map.put("imagecontent",R.drawable.face);
            map.put("img", R.drawable.defalt_head);
            map.put("username", "Kobe");
            map.put("date", "2014-7-29");
            map.put("phone", "PowerDesigner设计的时候同名对应到同一个属性");
            map.put("address", "怎么办，改了一个另一个也跟着改，数据库比较大，删除工作量太大了！");
            map.put("count", "2");
        if(!isHead){
            list.addLast(map1);
            list.addLast(map);
            list.addLast(result);

        }else{
            list.addFirst(map1);
        }


        //通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
        adapter.notifyDataSetChanged();
        // Call onRefreshComplete when the list has been refreshed.
        pullToRefreshListView.onRefreshComplete();
        System.out.println("refresh1");
        System.out.println(list.size());
        super.onPostExecute(result);
    }
}
