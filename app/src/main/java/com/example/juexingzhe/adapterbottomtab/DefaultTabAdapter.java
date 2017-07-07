package com.example.juexingzhe.adapterbottomtab;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juexingzhe on 2017/6/28.
 */

public class DefaultTabAdapter extends TabAdapter {

    private List<TabBean> mData = new ArrayList<>();

    private ViewHolder viewHolder;


    DefaultTabAdapter(ArrayList<TabBean> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }


    @Override
    public View getView(ViewGroup parent, View convertView, final int position) {

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView =  inflater.inflate(R.layout.tab_item, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.tab_img);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tab_txt);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setBackgroundResource(mData.get(position).tabImgSourceUnSelect);
        viewHolder.textView.setText(mData.get(position).tabTxt);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        convertView.setLayoutParams(layoutParams);

        return convertView;
    }

    @Override
    public TabBean getItem(int position) {
        return mData.get(position);
    }


    private static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
