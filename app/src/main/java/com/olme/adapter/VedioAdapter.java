package com.olme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.olme.R;
import com.olme.domain.VedioViewHolder;

import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 15-3-21.
 */
public class VedioAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<HashMap<String,Object>> list;

    public VedioAdapter(Context context) {
        this.layoutInflater = layoutInflater.from(context);
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
        final VedioViewHolder vedioViewHolder;

        if (convertView == null ) {
            convertView = layoutInflater.inflate(R.layout.item_vedioinfo, null);
            vedioViewHolder = new VedioViewHolder();
            vedioViewHolder.vedioName = (TextView)convertView.findViewById(R.id.vedioInfoName);
            vedioViewHolder.vedioLength = (TextView)convertView.findViewById(R.id.vedioInfoVedioLength);

            convertView.setTag(vedioViewHolder);

        }else{

            vedioViewHolder = (VedioViewHolder)convertView.getTag();
        }

        vedioViewHolder.vedioName.setText(getList().get(position).get("vedioName").toString());
        vedioViewHolder.vedioLength.setText(getList().get(position).get("vedioLength").toString());

        return convertView;
    }
}
