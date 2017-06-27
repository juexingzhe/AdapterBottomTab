package com.example.juexingzhe.adapterbottomtab;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by juexingzhe on 2017/6/28.
 */

public class TabLinearLayout extends LinearLayout{

    private TabAdapter<String> mTabAdapter;

    public TabLinearLayout(Context context) {
        super(context);
        init();
    }

    public TabLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TabLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        this.setOrientation(HORIZONTAL);
    }

    private DataSetObserver mTabDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            tabOnChanged();

        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            removeAllViews();
        }
    };


    public void setTabAdapter(TabAdapter mTabAdapter) {
        this.mTabAdapter = mTabAdapter;
        removeAllViews();
        mTabAdapter.registerObserver(mTabDataSetObserver);
        int count = mTabAdapter.getCount();
        View view;
        for (int i = 0; i < count; i++){
            view = mTabAdapter.getView(this, i);
            addView(view);
        }

    }

    private void tabOnChanged(){
        int childCount = getChildCount();
        int count = mTabAdapter.getCount();


        if (childCount > count) removeViews(count, childCount -count);

        for (int i = 0; i < count; i++){
            if (i < childCount){
                TextView view = (TextView)getChildAt(i);
                view.setText(mTabAdapter.getItem(i));
            }else {
                TextView textView = (TextView) mTabAdapter.getView(this, i);
                addView(textView);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int width = getWidth();
        int splitWidth = getSplitWidth(width, getChildCount());


        int margin = (splitWidth - getChildAt(0).getWidth()) / 2;
        for (int i =0 ; i< getChildCount(); i++){
            ViewGroup.MarginLayoutParams layoutParams = (MarginLayoutParams)getChildAt(i).getLayoutParams();
            layoutParams.leftMargin = margin;
            layoutParams.rightMargin = margin;
        }


    }

    private int getSplitWidth(int width, int num){
        int r = width / num;
        // if the signs are different and modulo not zero, round down
        if ((width ^ num) < 0 && (r * num != width)) {
            r--;
        }
        return r;
    }


}
