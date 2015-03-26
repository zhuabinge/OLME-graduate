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
import com.olme.domain.CommunicationViewHolder;
import com.olme.tool.UrlUtil;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by root on 15-3-22.
 */
public class CommunicationAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private LinkedList<HashMap<String,Object>> list;
    private File cache;

    public CommunicationAdapter(Context context, File cache) {
        this.layoutInflater = layoutInflater.from(context);
        this.cache = cache;
    }

    public LinkedList<HashMap<String, Object>> getList() {
        return list;
    }

    public void setList(LinkedList<HashMap<String, Object>> list) {
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
        final CommunicationViewHolder communicationViewHolder;

        if (convertView == null ) {
            convertView = layoutInflater.inflate(R.layout.item_question, null);
            communicationViewHolder = new CommunicationViewHolder();
            communicationViewHolder.themeUserName = (TextView)convertView.findViewById(R.id.themeUserName);
            communicationViewHolder.themeUserHeadPhoto = (ImageView)convertView.findViewById(R.id.themeUserHeadPhoto);
            communicationViewHolder.themeDate = (TextView)convertView.findViewById(R.id.themeDate);
            communicationViewHolder.themeName = (TextView)convertView.findViewById(R.id.themeName);
            communicationViewHolder.themContent = (TextView)convertView.findViewById(R.id.themContent);
            communicationViewHolder.themeCommentCount = (TextView)convertView.findViewById(R.id.themeCommentCount);
            convertView.setTag(communicationViewHolder);

        }else{
            communicationViewHolder = (CommunicationViewHolder)convertView.getTag();
        }

        communicationViewHolder.themeUserName.setText(getList().get(position).get("userName").toString());
        communicationViewHolder.themeDate.setText(getList().get(position).get("questionStartTime").toString());
        communicationViewHolder.themeName.setText(getList().get(position).get("questionTheme").toString());
        communicationViewHolder.themContent.setText(getList().get(position).get("comContent").toString());
        communicationViewHolder.themeCommentCount.setText(getList().get(position).get("answerCount").toString());
        String path = UrlUtil.getUrl(getList().get(position).get("userPhoto").toString());
        AsyncImageTask task = new AsyncImageTask(path, cache, communicationViewHolder.themeUserHeadPhoto);
        task.execute();

        return convertView;
    }
}
