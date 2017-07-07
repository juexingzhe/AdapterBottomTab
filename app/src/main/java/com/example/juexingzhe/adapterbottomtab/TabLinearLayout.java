package com.example.juexingzhe.adapterbottomtab;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by juexingzhe on 2017/6/28.
 */

public class TabLinearLayout extends LinearLayout {

    private static final int MAX_ITEM_COUNT = 5;

    private TabAdapter<TabBean> mTabAdapter;

    private ArrayList<LinearLayout> mContainer;

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

    private void init() {
        this.setOrientation(HORIZONTAL);
        mContainer = new ArrayList<>();
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


    public void setTabAdapter(TabAdapter tabAdapter) {
        this.mTabAdapter = tabAdapter;
        removeAllViews();
        mTabAdapter.registerObserver(mTabDataSetObserver);
        mTabAdapter.notifyChanged();
    }

    private void tabOnChanged() {
        removeAllViews();
        mContainer.clear();
        int count = mTabAdapter.getCount();

        if (count > MAX_ITEM_COUNT) {
            count = MAX_ITEM_COUNT;
            Toast.makeText(getContext(), "最多5个Tab", Toast.LENGTH_SHORT).show();
        }

        for (int i = 0; i < count; i++) {
            LinearLayout layout = (LinearLayout) mTabAdapter.getView(this, null, i);
            final int finalI = i;
            layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyClickEvent(finalI);
                }
            });
            addView(layout);
            mContainer.add(layout);
        }
    }

    private void notifyClickEvent(int finalI) {
        for (int i = 0; i < mContainer.size(); i++) {
            if (i == finalI) {
                mContainer.get(i).getChildAt(0).setBackgroundResource(mTabAdapter.getItem(i).tabImgSourceSelected);
                continue;
            }
            mContainer.get(i).getChildAt(0).setBackgroundResource(mTabAdapter.getItem(i).tabImgSourceUnSelect);
        }

    }

}
