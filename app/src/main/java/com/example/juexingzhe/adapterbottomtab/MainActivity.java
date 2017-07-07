package com.example.juexingzhe.adapterbottomtab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<TabBean> datas = new ArrayList<>();

    private Button mAdd;
    private Button mDelete;
    private TabLinearLayout tabLinearLayout;
    private DefaultTabAdapter defaultTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLinearLayout = (TabLinearLayout) findViewById(R.id.bottom_tab);
        mAdd = (Button) findViewById(R.id.add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.add(new TabBean(R.drawable.view_selected, R.drawable.view, "活动"));
                defaultTabAdapter.notifyChanged();
            }
        });
        mDelete = (Button) findViewById(R.id.delete);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.size() < 4) {
                    Toast.makeText(MainActivity.this, "最少3个Tab", Toast.LENGTH_SHORT).show();
                    return;
                }
                datas.remove(datas.size() - 1);
                defaultTabAdapter.notifyChanged();
            }
        });

        datas.add(new TabBean(R.drawable.home_selected, R.drawable.home, "首页"));
        datas.add(new TabBean(R.drawable.home_selected, R.drawable.home, "发现"));
        datas.add(new TabBean(R.drawable.home_selected, R.drawable.home, "设置"));

        defaultTabAdapter = new DefaultTabAdapter(datas);
        tabLinearLayout.setTabAdapter(defaultTabAdapter);


    }
}
