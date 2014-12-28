package com.olme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.olme.activity.CourseInfoDetailActivity_;
import com.olme.activity.R;
import com.olme.dataSource.CourseInfoData;


/**
 * Created by Bingo on 2014/8/4.
 * app底部菜单的课程
 */

public class CourseFragment extends Fragment {

    private SimpleAdapter adapter;
    private CourseInfoData data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.tab1, null);

        final ListView listView1 = (ListView) view.findViewById(R.id.courseInfoList);
        adapter = new SimpleAdapter(getActivity(), data.getDataSource(), R.layout.item_courseinfo,
                new String[] { "courseInfoPhoto", "courseInfoName","courseInfoTeacherName","courseInfoTotalNum"},
                new int[] {R.id.courseInfoPhoto, R.id.courseInfoName, R.id.courseInfoTeacherName, R.id.courseInfoTotalNum});
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),CourseInfoDetailActivity_.class);
                intent.putExtra("itemIndex",position);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
