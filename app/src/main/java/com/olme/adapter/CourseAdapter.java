package com.olme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.olme.R;
import com.olme.asyncTask.AsyncImageTask;
import com.olme.domain.CourseViewHolder;
import com.olme.tool.UrlUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 15-3-17.
 */
public class CourseAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<HashMap<String,Object>> list;
    private File cache;

    public CourseAdapter(Context context, File cache) {
        this.layoutInflater = layoutInflater.from(context);
        this.cache = cache;
    }

    public List<HashMap<String, Object>> getList() {
        return list;
    }

    public void setList(List<HashMap<String, Object>> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return getList().size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final CourseViewHolder courseViewHolder;

        if (convertView == null ) {
            convertView = layoutInflater.inflate(R.layout.item_courseinfo, null);
            courseViewHolder = new CourseViewHolder();
            courseViewHolder.courseName = (TextView)convertView.findViewById(R.id.courseInfoName);
            courseViewHolder.coursePhoto = (ImageView)convertView.findViewById(R.id.courseInfoPhoto);

            convertView.setTag(courseViewHolder);

        }else{

            courseViewHolder = (CourseViewHolder)convertView.getTag();
        }

        courseViewHolder.courseName.setText(getList().get(position).get("courseName").toString());
        String path = UrlUtil.getUrl(getList().get(position).get("coursePhoto").toString());
        AsyncImageTask task = new AsyncImageTask(path, cache, courseViewHolder.coursePhoto);
        task.execute();

        return convertView;
    }
}