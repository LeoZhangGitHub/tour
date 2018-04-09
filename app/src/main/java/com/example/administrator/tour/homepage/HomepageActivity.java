package com.example.administrator.tour.homepage;

/**
 * Created by Administrator on 2018/4/3/003.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.administrator.tour.R;

import java.util.ArrayList;

public class HomepageActivity extends FragmentActivity {

    private ArrayList<Fragment> fragmentlist;
    private ViewPager mviewpager;
    private View viewbar;
    private int currIndex;//当前页卡编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        initView();
       // initBar();
        InitViewPager();
    }

    //初始化标签
    public void initView() {
        TextView homepageSite = (TextView) findViewById(R.id.id_homepage_site);
        TextView homepageHotel = (TextView) findViewById(R.id.id_homepage_hotel);
        TextView homepageTraffic = (TextView) findViewById(R.id.id_homepage_traffic);
        TextView homepageGroupTicket = (TextView) findViewById(R.id.id_homepage_group_ticket);

        homepageSite.setOnClickListener(new txListner(0));
        homepageHotel.setOnClickListener(new txListner(1));
        homepageTraffic.setOnClickListener(new txListner(2));
        homepageGroupTicket.setOnClickListener(new txListner(3));
    }

    //监听页面切换
    private class txListner implements View.OnClickListener {
        private int index = 0;
        public txListner (int i){
            index = i;
        }
        public void onClick(View v){
            mviewpager.setCurrentItem(index);
        }
    }
    //页面切换时滚动条移动
    public void initBar() {
        viewbar = (View) findViewById(R.id.id_bar);

        //得到屏幕的宽度
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        // 设置滚动条宽度为屏幕宽度的1/3
        int  tabLineLength = width / 4;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) viewbar.getLayoutParams();
        lp.width = tabLineLength;
        viewbar.setLayoutParams(lp);
    }

    /*
 * 初始化ViewPager
 */
    public void InitViewPager(){
       // mviewpager = (ViewPager)findViewById(R.id.id_viewpager);
        fragmentlist = new ArrayList<Fragment>();

        //给ViewPager设置适配器
        mviewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentlist));
        mviewpager.setCurrentItem(0);//设置当前显示标签页为第一页
        mviewpager.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            // 取得该控件的实例
            LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) viewbar.getLayoutParams();

            if(currIndex == arg0){
                ll.leftMargin = (int) (currIndex * viewbar.getWidth() + arg1
                        * viewbar.getWidth());
            }else if(currIndex > arg0){
                ll.leftMargin = (int) (currIndex * viewbar.getWidth() - (1 - arg1)* viewbar.getWidth());
            }
            viewbar.setLayoutParams(ll);
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int arg0) {
            currIndex = arg0;
        }
    }
}
