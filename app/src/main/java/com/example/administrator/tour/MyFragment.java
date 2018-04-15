package com.example.administrator.tour;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.tour.homepage.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/21/021.
 */

public class MyFragment extends Fragment implements View.OnClickListener{
    private String content;
    private View view;

    //UI Object
    private TextView txt_topbar;
    private TextView homepageSite;
    private TextView homepageHotel;
    private TextView homepageTraffic;
    private TextView homeGroupTicket;

    //Fragment Object
    private HomepageMyFragment fg1, fg2, fg3, fg4;
    private FragmentManager fManager;

    private FrameLayout mviewpager;
    private View viewbar;

    private int currIndex;

    private ViewPager viewPager;

    private TextView name_id;


    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private MyFragmentPagerAdapter mAdapter;


    public MyFragment(String content) {
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /******************************homepage页面************************************/
        if (content.equals("homepage")) {
            view = inflater.inflate(R.layout.activity_homepage, container, false);

            mviewpager = (FrameLayout)view.findViewById(R.id.lya_content);

            fManager = getFragmentManager();
            bindViews();
            homepageSite.performClick();   //模拟一次点击，既进去后选择第一项

            //给ViewPager设置适配器

//            mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
//            viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentlist));
           // viewPager.setCurrentItem(2);//设置当前显示标签页为第一页

            /******************************browse页面************************************/
        } else if (content.equals("browse")) {
            view = inflater.inflate(R.layout.activity_browse, container, false);


            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            List<BrowseData> dataList = new ArrayList<>();
            //for (int i = 0; i < 40; i++) {
                BrowseData data = new BrowseData("成都火锅大全", "成都，真正属于吃货的天堂，成都人不仅爱吃，而且会吃。","site1");
                BrowseData data1 = new BrowseData("今年最火的古镇群", "趁着天气还不是很热，出去玩儿的心是不是又开始蠢蠢欲动了？快来这里看看。","site2");
                BrowseData data2 = new BrowseData("来自北方的10大古镇", "相对于南方古镇的婉约，北方古镇则显得豪放、大气。。","site3");
                dataList.add(data);
                dataList.add(data1);
                dataList.add(data2);
           // }
            MyRecyclerAdapter adapter = new MyRecyclerAdapter(getActivity(), dataList);


            adapter.setOnRecyclerViewListener(new MyRecyclerAdapter.OnRecyclerViewListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), ArticleActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });

            recyclerView.setAdapter(adapter);


            /******************************mine页面************************************/
        } else if (content.equals("mine")) {
            view = inflater.inflate(R.layout.activity_mine, container, false);

            //设置头像为圆形

            RoundImageView img_round = (RoundImageView) view.findViewById(R.id.head);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.head);
            img_round.setBitmap(bitmap);

            name_id = (TextView) view.findViewById(R.id.name);
            name_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        return view;
    }


    private void bindViews() {
        txt_topbar = (TextView) view.findViewById(R.id.txt_topbar);
        homepageSite = (TextView) view.findViewById(R.id.id_homepage_site);
        homepageHotel = (TextView) view.findViewById(R.id.id_homepage_hotel);
        homepageTraffic = (TextView) view.findViewById(R.id.id_homepage_traffic);
        homeGroupTicket = (TextView) view.findViewById(R.id.id_homepage_group_ticket);

        homepageSite.setOnClickListener(this);
        homepageHotel.setOnClickListener(this);
        homepageTraffic.setOnClickListener(this);
        homeGroupTicket.setOnClickListener(this);

        viewbar = (View) view.findViewById(R.id.id_bar);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        homepageTraffic.setSelected(false);
        homeGroupTicket.setSelected(false);
        homepageHotel.setSelected(false);
        homepageSite.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(android.app.FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }

    @Override
    public void onClick(View v) {
        android.app.FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.id_homepage_site:
                setSelected();
                homepageSite.setSelected(true);
                if(fg1 == null){
                    fg1 = new HomepageMyFragment("homepage_site");
                    fTransaction.add(R.id.lya_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.id_homepage_traffic:
                setSelected();
                homepageTraffic.setSelected(true);
                if(fg2 == null){
                    fg2 = new HomepageMyFragment("homepage_traffic");
                    fTransaction.add(R.id.lya_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.id_homepage_hotel:
                setSelected();
                homepageHotel.setSelected(true);
                if(fg3 == null){
                    fg3 = new HomepageMyFragment("homepage_hotel");
                    fTransaction.add(R.id.lya_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.id_homepage_group_ticket:
                setSelected();
                homeGroupTicket.setSelected(true);
                if(fg4 == null){
                    fg4 = new HomepageMyFragment("homepage_group_ticket");
                    fTransaction.add(R.id.lya_content,fg4);
                }else{
                    fTransaction.show(fg4);
                }
                break;

        }
        fTransaction.commit();
    }

    /*@Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.id_homepage_site:
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.id_homepage_traffic:
                viewPager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.id_homepage_hotel:
                viewPager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.id_homepage_group_ticket:
                viewPager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }

*/
//    //重写ViewPager页面切换的处理方法
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//        /*//state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
//        if (state == 2) {
//            switch (viewPager.getCurrentItem()) {
//                case PAGE_ONE:
//                    rb_channel.setChecked(true);
//                    break;
//                case PAGE_TWO:
//                    rb_message.setChecked(true);
//                    break;
//                case PAGE_THREE:
//                    rb_better.setChecked(true);
//                    break;
//            }
//        }*/
//    }

}