package com.example.administrator.tour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/31/031.
 */

public class ShareSomething extends Activity{
    private Button addButton;
    private TextView articleTitle;
    private TextView articleContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_something);

        articleTitle = (EditText) findViewById(R.id.article_title);
        articleContent = (EditText) findViewById(R.id.article_content);

        addButton = (Button) findViewById(R.id.button_add_);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                 /*   System.out.println("afsfasf");
                    System.out.println(articleTitle.getText());*/
                    /*if (articleTitle.getText().length() < 1) {
                        Toast.makeText(ShareSomething.this, "请输入标题！", Toast.LENGTH_SHORT).show();
                        if (articleContent.getText().length() < 1) {
                            Toast.makeText(ShareSomething.this, "没有任何内容！", Toast.LENGTH_SHORT).show();
                        } else {*/
                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("doWhat", "article");
                            jsonObject.put("Title", articleTitle.getText());
                            jsonObject.put("Content",articleContent.getText());
                            final String  result = jsonObject.toString();
                            System.out.println(result);
                            submit();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

       }

    public void submit() throws JSONException {

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("doWhat", "article");
        jsonObject.put("Title", articleTitle.getText());
        jsonObject.put("Content",articleContent.getText());
        final String  result = jsonObject.toString();
        System.out.println(result);
        //发送到服务器
        SendResultToServer.getInstance().commit(result);

        Toast.makeText(ShareSomething.this, "发表成功", Toast.LENGTH_SHORT).show();

        //返回上一个页面
        finish();
    }
}


