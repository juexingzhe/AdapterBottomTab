package com.example.juexingzhe.adapterbottomtab;

import android.database.DataSetObservable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by juexingzhe on 2017/6/28.
 */

public abstract class TabAdapter<T> extends DataSetObservable {


    public abstract int getCount();

    public abstract View getView(ViewGroup parent, View convertView, int position);


    public abstract T getItem(int position);

}
