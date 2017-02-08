package com.czhappy.commonindexdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.czhappy.commonindexdemo.R;
import com.czhappy.commonindexdemo.model.Classfly;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/12 0012
 * Time: 16:07
 */
public class ClassflyListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private List<Classfly> list;

    public ClassflyListAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.list =  new ArrayList<Classfly>();

    }

    public void setItems(List<Classfly> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.classfly_list_item, null);
            holder.image_iv = (ImageView)convertView.findViewById(R.id.image_iv);
            holder.name_tv = (TextView)convertView.findViewById(R.id.name_tv);
            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        final Classfly c = (Classfly) getItem(position);
        holder.name_tv.setText(c.getName());
        holder.image_iv.setImageResource(c.getDrawable());
        return convertView;
    }

    private final class ViewHolder {
        private ImageView image_iv;
        private TextView name_tv;
    }
}
