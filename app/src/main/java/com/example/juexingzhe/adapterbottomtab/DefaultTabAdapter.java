package com.example.juexingzhe.adapterbottomtab;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juexingzhe on 2017/6/28.
 */

public class DefaultTabAdapter<T> extends TabAdapter {

    private List<T> mDatas = new ArrayList<>();

    private int maxLengthItemPos = 0;

    public DefaultTabAdapter(ArrayList<T> datas) {
        mDatas = datas;
    }

    public int getMaxLengthItemPos(){
        maxLengthItemPos = mDatas.get(0).toString().length();

        for (int i = 1; i < mDatas.size(); i++){
            if (mDatas.get(i).toString().length() > maxLengthItemPos){
                maxLengthItemPos = mDatas.get(i).toString().length();
            }

        }

        return maxLengthItemPos;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }


    @Override
    public View getView(View parent, int position) {

        TextView textView = new TextView(parent.getContext());
        textView.setText((String)mDatas.get(position));
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setSingleLine();
        return textView;
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }
}
