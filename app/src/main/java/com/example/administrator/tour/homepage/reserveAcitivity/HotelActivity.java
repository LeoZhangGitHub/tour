package com.example.administrator.tour.homepage.reserveAcitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.tour.ArticleActivity;
import com.example.administrator.tour.BrowseData;
import com.example.administrator.tour.MyRecyclerAdapter;
import com.example.administrator.tour.R;
import com.example.administrator.tour.homepage.Data;
import com.example.administrator.tour.homepage.HomepageHotelRecylerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class HotelActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(HotelActivity.this, LinearLayoutManager.VERTICAL, false));
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            Data data = new Data("标题","500kuai","fasdfadsasf");
            dataList.add(data);
        }
        HomepageHotelRecylerAdapter adapter = new HomepageHotelRecylerAdapter(HotelActivity.this, dataList);


        adapter.setOnRecyclerViewListener(new HomepageHotelRecylerAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
               /* Intent intent = new Intent(HotelActivity.this, ArticleActivity.class);
                startActivity(intent);*/
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        recyclerView.setAdapter(adapter);
    }
}
