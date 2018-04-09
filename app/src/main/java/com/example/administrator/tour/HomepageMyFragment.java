package com.example.administrator.tour;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.administrator.tour.homepage.Data;
import com.example.administrator.tour.homepage.HomepageGroupTicketRecyclerAdapter;
import com.example.administrator.tour.homepage.HomepageSiteRecyclerAdapter;
import com.example.administrator.tour.homepage.HomepageTrafficRecyclerAdapter;
import com.example.administrator.tour.homepage.reserveAcitivity.HotelActivity;
import com.example.administrator.tour.homepage.reserveAcitivity.SiteReserveActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class HomepageMyFragment extends Fragment{

    private View view;
    private String content;
    public HomepageMyFragment(String content) {
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /******************************homepage_site************************************/
        if (content.equals("homepage_site")) {
            view = inflater.inflate(R.layout.activity_browse, container, false);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            List<Data> dataList = new ArrayList<>();
            //for (int i = 0; i < 40; i++) {
                Data data = new Data("淮南八日游", "RMB:5000","淮南师范学院/八公山/龙湖公园/豪华豆腐宴");
                dataList.add(data);
                Data data1 = new Data("上海三日游", "RMB:3000","东方明珠/外滩/专车接送/迪士尼/南京西路");
                dataList.add(data1);
            //}
            HomepageSiteRecyclerAdapter adapter = new HomepageSiteRecyclerAdapter(getActivity(), dataList);

            adapter.setOnRecyclerViewListener(new HomepageSiteRecyclerAdapter.OnRecyclerViewListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), SiteReserveActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });

            recyclerView.setAdapter(adapter);

        /******************************homepage_traffic************************************/
        } else if (content.equals("homepage_traffic")) {
            view = inflater.inflate(R.layout.activity_browse, container, false);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            List<Data> dataList = new ArrayList<>();
            //for (int i = 0; i < 40; i++) {
                Data data = new Data("淮南到上海", "RMB:200");
                Data data1 = new Data("南京到北京", "RMB:389");
                Data data2 = new Data("合肥到淮南", "RMB:20");
                Data data3 = new Data("黄山到合肥", "RMB:123");

                dataList.add(data);
                dataList.add(data1);
                dataList.add(data2);
                dataList.add(data3);
            //}
            HomepageTrafficRecyclerAdapter adapter = new HomepageTrafficRecyclerAdapter(getActivity(), dataList);

            adapter.setOnRecyclerViewListener(new HomepageTrafficRecyclerAdapter.OnRecyclerViewListener() {
                @Override
                public void onItemClick(View view, int position) {
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });

            recyclerView.setAdapter(adapter);
            /**
             * will crash
             */
            /*Button button = (Button) view.findViewById(R.id.button_traffic_reserve);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "您已成功预定！", Toast.LENGTH_LONG).show();
                }
            });*/


            /******************************homepage_group_ticket************************************/
        } else if (content.equals("homepage_group_ticket")) {
            view = inflater.inflate(R.layout.activity_browse, container, false);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            List<Data> dataList = new ArrayList<>();
            for (int i = 0; i < 40; i++) {
                Data data = new Data("标题", "500","23123哈哈或或或或或或或或或或或或或或或或或","8geren");
                dataList.add(data);
            }
            HomepageGroupTicketRecyclerAdapter adapter = new HomepageGroupTicketRecyclerAdapter(getActivity(), dataList);


            adapter.setOnRecyclerViewListener(new HomepageGroupTicketRecyclerAdapter.OnRecyclerViewListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), SiteReserveActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });

            recyclerView.setAdapter(adapter);

            /******************************homepage_hotel******************************************/
        } else if (content.equals("homepage_hotel")) {
            view = inflater.inflate(R.layout.activity_homepage_hotel, container, false);

            EditText baginTime = (EditText) view.findViewById(R.id.editText_beginTime);
            EditText endTime = (EditText) view.findViewById(R.id.editText_endTime);

            Button button = (Button) view.findViewById(R.id.button_hotel_reserve);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), HotelActivity.class);
                    startActivity(intent);
                }
            });


        }
        return view;
    }
}

