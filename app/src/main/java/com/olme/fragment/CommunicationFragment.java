package com.olme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.olme.activity.AddThemeActivity_;
import com.olme.activity.AnswerDetailActivity_;
import com.olme.activity.MessageDetailActivity_;
import com.olme.activity.R;
import com.olme.asyncTask.QuestionGetDatTask;
import com.olme.dataSource.AllQuestionData;


import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/4.
 * app底部菜单的交流
 */
public class CommunicationFragment extends Fragment {
    private LinkedList<HashMap<String,Object>> list;
    private PullToRefreshListView pullToRefreshListView;
    private SimpleAdapter adapter;
    private AllQuestionData data;
    private TextView messageCount;
    private TextView writePanel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.tab2, null);

        list =data.getDataSource();

        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.themeList);

        //设置pull-to-refresh模式为Mode.Both
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        // Set a listener to be invoked when the list should be refreshed.
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                    String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
                            DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                    // Update the LastUpdatedLabel
                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                if(refreshView.isHeaderShown()){
                    // Do work to refresh the list here.
                    new QuestionGetDatTask(list,pullToRefreshListView,adapter,true).execute();
                }
                else{
                    new QuestionGetDatTask(list,pullToRefreshListView,adapter,false).execute();
                }
            }
        });
        adapter = new SimpleAdapter(getActivity(), list, R.layout.item_question,
                new String[] {"img", "username","date", "phone", "address" ,"count"},
                new int[] { R.id.themeUserHeadPhoto, R.id.themeUserName, R.id.themeDate , R.id.themeName, R.id.themContent, R.id.themeCommentCount });

        ListView actualListView = pullToRefreshListView.getRefreshableView();
        actualListView.setAdapter(adapter);

        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),AnswerDetailActivity_.class);
                startActivity(intent);
            }
        });

        messageCount = (TextView)view.findViewById(R.id.messageCount);
        writePanel = (TextView)view.findViewById(R.id.writePanel);

        messageCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageDetailActivity_.class);
                startActivity(intent);
            }
        });

        writePanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddThemeActivity_.class);
                startActivityForResult(intent,1);
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 9) {
            String editTextValue = data.getStringExtra("editTextValue");
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("img", R.drawable.defalt_head);
            map.put("username", "Jordan");
            map.put("date", "2014-7-29");
            map.put("phone", title);
            map.put("address", content);
            map.put("count", "0");
            list.addFirst(map);
            //通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
            adapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            pullToRefreshListView.onRefreshComplete();
        }
    }
}
