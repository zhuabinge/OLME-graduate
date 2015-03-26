package com.olme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.olme.R;
import com.olme.domain.AnswerViewHolder;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by root on 15-3-24.
 */
public class AnswerDetailAdapter  extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private LinkedList<HashMap<String,Object>> list;

    public AnswerDetailAdapter(Context context) {
        this.layoutInflater = layoutInflater.from(context);
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
        final AnswerViewHolder answerViewHolder;

        if (convertView == null ) {
            convertView = layoutInflater.inflate(R.layout.item_answer, null);
            answerViewHolder = new AnswerViewHolder();
            answerViewHolder.userName = (TextView)convertView.findViewById(R.id.answerUserName);
            answerViewHolder.comTime = (TextView)convertView.findViewById(R.id.answerDate);
            answerViewHolder.comContent = (TextView)convertView.findViewById(R.id.answerContent);
            convertView.setTag(answerViewHolder);

        }else{
            answerViewHolder = (AnswerViewHolder)convertView.getTag();
        }

        answerViewHolder.userName.setText(getList().get(position).get("userName").toString());
        answerViewHolder.comTime.setText(getList().get(position).get("comTime").toString());
        answerViewHolder.comContent.setText(getList().get(position).get("comContent").toString());

        return convertView;
    }
}
