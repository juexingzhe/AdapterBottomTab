package com.example.juexingzhe.adapterbottomtab;

import android.database.DataSetObservable;
import android.view.View;

/**
 * Created by juexingzhe on 2017/6/28.
 */

public abstract class TabAdapter<T> extends DataSetObservable {



    public abstract int getCount();

    public abstract View getView(View parent, int position);


    public abstract T getItem(int position);

    public int getMaxLengthItemPos(){
        return 0;
    }


}
