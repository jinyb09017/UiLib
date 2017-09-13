package com.kakao.uidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kakao.uidemo.item.DemoItem;
import com.kakao.uilib.recycler.CommonRecyclerviewAdapter;
import com.kakao.uilib.recycler.MultiItemTypeRecyclerAdapter;
import com.kakao.uilib.recycler.base.ViewRecycleHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static List<DemoItem> helps = new ArrayList<>();

    static {
        helps.add(new DemoItem("toast demo", ToastActivity.class));
        helps.add(new DemoItem("pop demo", PopActivity.class));
        helps.add(new DemoItem("wheel demo", WheelActivity.class));
        helps.add(new DemoItem("dialog demo", DialogActivity.class));
        helps.add(new DemoItem("align demo", AlignTextActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final CommonRecyclerviewAdapter<DemoItem> commonRecyclerviewAdapter = new CommonRecyclerviewAdapter<DemoItem>(this, R.layout.item_demo) {
            @Override
            protected void convert(ViewRecycleHolder holder, DemoItem item, int position) {
                holder.setText(R.id.tv_name, (position + 1) + ":" + item.name);
            }

        };
        recyclerView.setAdapter(commonRecyclerviewAdapter);
        commonRecyclerviewAdapter.replaceAll(helps);

        commonRecyclerviewAdapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                DemoItem demoItem = commonRecyclerviewAdapter.getItem(position);
                startActivity(new Intent(MainActivity.this, demoItem.entity));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


    }
}
