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
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.tour.homepage.MyFragmentPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/21/021.
 */

public class MyFragment extends Fragment implements View.OnClickListener{
    private String content;
    private View view;

    private String username;
    private String phoneNumber;

    //UI Object
    private TextView txt_topbar;
    private TextView homepageSite;
    private TextView homepageHotel;
    private TextView homepageTraffic;
    private TextView homeGroupTicket;

    private String phoneNumberText;

    //Fragment Object
    private HomepageMyFragment fg1, fg2, fg3, fg4;
    private FragmentManager fManager;

    private FrameLayout mviewpager;
    private View viewbar;

    private int currIndex;

    private ViewPager viewPager;

    private TextView name_id;
    private TextView phone_number;

    private String browseData;


    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private SendDataToServerForSocket sendDataToServerForSocket;

    private MyFragmentPagerAdapter mAdapter;

    public static MyFragment instance;

    public static MyFragment getInstance(){
        if(instance == null){

            instance = new MyFragment("browse");

        }
        return instance;
    }


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

            System.out.println(this.browseData);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            List<BrowseData> dataList = new ArrayList<>();
            //for (int i = 0; i < 40; i++) {
            BrowseData data = new BrowseData("成都火锅大全", "成都，真正属于吃货的天堂，成都人不仅爱吃，而且会吃。","site1");
            BrowseData data1 = new BrowseData("今年最火的古镇群", "趁着天气还不是很热，出去玩儿的心是不是又开始蠢蠢欲动了？快来这里看看。","site2");
            BrowseData data2 = new BrowseData("来自北方的10大古镇", "相对于南方古镇的婉约，北方古镇则显得豪放、大气。。","site3");
            BrowseData data5 = new BrowseData("来自北方的10大古镇", "相对于南方古镇的婉约，北方古镇则显得豪放、大气。。","site3");
            BrowseData data6 = new BrowseData("来自北方的10大古镇", "相对于南方古镇的婉约，北方古镇则显得豪放、大气。。","site3");
            BrowseData data7 = new BrowseData("来自北方的10大古镇", "相对于南方古镇的婉约，北方古镇则显得豪放、大气。。","site3");
            BrowseData data3 = new BrowseData("来自北方的10大古镇", "相对于南方古镇的婉约，北方古镇则显得豪放、大气。。","site3");
            BrowseData data4 = new BrowseData("来自北方的10大古镇", "相对于南方古镇的婉约，北方古镇则显得豪放、大气。。","site3");
            BrowseData data8 = new BrowseData("来自北方的10大古镇", "相对于南方古镇的婉约，北方古镇则显得豪放、大气。。","site3");

            dataList.add(data);
                dataList.add(data1);
                dataList.add(data1);
                dataList.add(data2);
                dataList.add(data3);
                dataList.add(data4);
                dataList.add(data5);
                dataList.add(data6);
                dataList.add(data7);
                dataList.add(data8);
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

            //设置用户名
            name_id = (TextView) view.findViewById(R.id.name);
            name_id.setText(username);

            //获取电话号码
            phone_number = (TextView) view.findViewById(R.id.phone_number);

            sendDataToServerForSocket =
                    new SendDataToServerForSocket(username);

            sendDataToServerForSocket.SendDataToServer();

            /*//设置android客户端和服务器的连接 进行登录
            try {
                new Thread() {
                    @Override
                    public void run() {

                        sendDataToServerForSocket.SendDataToServer();

                       *//* try {
                            phoneNumberText = sendDataToServerForSocket.getUserPhoneNumber(username);
                            System.out.println(phoneNumberText);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }*//*
*//*
                        //设置电话号码
                        phone_number.setText(phoneNumberText);*//*

                    }
                }.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
*/

            phone_number.setText("手机号码：15955487001");
            /*phone_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                phoneNumberText = sendDataToServerForSocket.getUserPhoneNumber("wwoshinidie");
                                System.out.println("******"+phoneNumberText);
                            } catch (IOException e) {
                                //
                            }
                        }
                    }.start();
                    *//*try {
                        submit();
                        //sendPic();
                    } catch (JSONException e) {

                    }*//*
                }
            });*/

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

    public void sendPic() throws IOException{

        new Thread(new  Runnable() {
            @Override
            public void run() {
                try {

                    //1.连接诶服务器
                    Socket s = new Socket("127.0.0.1", 5612);
                    System.out.println("已连接到服务器5612端口，准备传送图片...");
                    //获取图片字节流
                    FileInputStream fis = new FileInputStream("head.jpg");
                    //获取输出流
                    OutputStream out = s.getOutputStream();
                    byte[] buf = new byte[1024];
                    int len = 0;
                    //2.往输出流里面投放数据
                    while ((len = fis.read(buf)) != -1) {
                        out.write(buf, 0, len);
                    }
                    //通知服务端，数据发送完毕
                    s.shutdownOutput();
                    //3.获取输出流，接受服务器传送过来的消息“上传成功”
                    InputStream in = s.getInputStream();
                    byte[] bufIn = new byte[1024];
                    int num = in.read(bufIn);
                    System.out.println(new String(bufIn, 0, num));
                    //关闭资源
                    fis.close();
                    out.close();
                    in.close();
                    s.close();
                } catch (IOException e) {

                }
            }
        }).start();
    }

    public void setBrowseData(String result) {
        this.browseData = result;
    }
    public void submit() throws JSONException{

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("doWhat", "site");
        jsonObject.put("name", "zhang");
        jsonObject.put("password", "123");
        final String  result=jsonObject.toString();

        new Thread(new  Runnable() {
            @Override
            public void run() {

                try {
                    Socket socket=new Socket(InetAddress.getByName("192.168.138.84"), 12345);
                    OutputStream os=socket.getOutputStream();
                    os.write(result.getBytes());
                    os.flush();
                    //防止服务端read方法读阻塞
                    socket.shutdownOutput();
                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void getPhoneNumberText(String phoneNumberText, String username) {

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

    public void getUsername(String username) {
        this.username = username;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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