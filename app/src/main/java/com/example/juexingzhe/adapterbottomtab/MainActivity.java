package com.example.juexingzhe.adapterbottomtab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<String> datas = new ArrayList<>();

    private Button mAdd;
    private Button mDelete;
    private TabLinearLayout tabLinearLayout;
    private DefaultTabAdapter<String> stringDefaultTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLinearLayout = (TabLinearLayout)findViewById(R.id.bottom_tab);
        mAdd = (Button) findViewById(R.id.add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.add("活动");
                datas.add("详情");
                datas.add("购物");
                stringDefaultTabAdapter.notifyChanged();
            }
        });
        mDelete = (Button)findViewById(R.id.delete);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.remove("活动");
                datas.remove("详情");
                datas.remove("购物");
                stringDefaultTabAdapter.notifyChanged();
            }
        });

        datas.add("首页");
        datas.add("发现");
        datas.add("设置");

        stringDefaultTabAdapter = new DefaultTabAdapter<>(datas);
        tabLinearLayout.setTabAdapter(stringDefaultTabAdapter);



    }
}
