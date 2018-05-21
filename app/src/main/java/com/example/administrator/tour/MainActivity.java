package com.example.administrator.tour;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaocheng_zhang on 2018/3/01 .
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //添加发表内容按钮
    private Button addButton;

    //UI Object
    private TextView txt_topbar;
    private TextView txt_homepage;
    private TextView txt_browse;
    private TextView txt_mine;

    private String username;


    //fragment
    private FrameLayout ly_content;
    //Fragment Object
    private MyFragment fg1,fg2,fg3;
    private FragmentManager fManager;

    private static SendDataToServerForSocket sendDataToServerForSocket;

    //登录页面
    private EditText editAccount,editPassword;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1){
            Toast.makeText( com.example.administrator.tour.MainActivity.this, "we",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_main);
        editAccount=(EditText)findViewById(R.id.loginAccount_id);
        editPassword=(EditText)findViewById(R.id.password_id);

        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        bindViews();
        txt_homepage.performClick();   //模拟一次点击，既进去后选择第一项

        addButton = (Button) findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.administrator.tour.MainActivity.this, ShareSomething.class);
                startActivity(intent);
            }
        });

    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        txt_homepage = (TextView) findViewById(R.id.txt_homepage);
        txt_browse = (TextView) findViewById(R.id.txt_browse);
        txt_mine = (TextView) findViewById(R.id.txt_mine);

        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_homepage.setOnClickListener(this);
        txt_browse.setOnClickListener(this);
        txt_mine.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_homepage.setSelected(false);
        txt_browse.setSelected(false);
        txt_mine.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        addButton = (Button) findViewById(R.id.button_add);

        switch (v.getId()){
            case R.id.txt_homepage:
                setSelected();
                txt_homepage.setSelected(true);

                //设置addbutton隐藏
                addButton.setVisibility(View.INVISIBLE);

                if(fg1 == null){
                    fg1 = new MyFragment("homepage");
                    fTransaction.add(R.id.ly_content,fg1);

                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_browse:
                setSelected();
                txt_browse.setSelected(true);

                //设置addbutton显示
                addButton.setVisibility(View.VISIBLE);

                if(fg2 == null){
                    fg2 = new MyFragment("browse");
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }

               /* JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("doWhat", "getbrowse");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final String  result=jsonObject.toString();

                sendDataToServerForSocket =
                        new SendDataToServerForSocket("zhang");

                new Thread(){
                    @Override
                    public void run() {
                        try {
                            String aa = sendDataToServerForSocket.getArticleData(result);
                            MyFragment.getInstance().setBrowseData(aa);
                            System.out.println("+++"+aa);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();*/


                break;
            case R.id.txt_mine:
                setSelected();
                txt_mine.setSelected(true);  //可以被选

                //设置addbutton隐藏
                addButton.setVisibility(View.INVISIBLE);

                //通过Intent获取username
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                setUsername(username);

                if(fg3 == null){
                    fg3 = new MyFragment("mine");
                    fg3.getUsername(username);
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;

        }
        fTransaction.commit();
    }

    private void setUsername(String username) {
        this.username = username;
    }
}
