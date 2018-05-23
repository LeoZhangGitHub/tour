package com.example.administrator.tour;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.tour.R;
import com.example.administrator.tour.SendDataToServerForSocket;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2018/5/21/021.
 */

public class MyOrderActivity extends AppCompatActivity {
    private SendDataToServerForSocket sendDataToServerForSocket;
    private String projectname;
    private String price;
    private JSONObject jsonObject;
    private LinearLayout ll;
    private TextView textView;

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (msg.arg1 == 1) {
            }

            try {
                projectname = jsonObject.get("projectname").toString();
                price = jsonObject.get("price").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            textView.setText("订单内容: "+projectname+", 价格："+price);
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
            ll.addView(textView);

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("doWhat", "getMyOrder");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String result = jsonObject.toString();

        sendDataToServerForSocket =
                new SendDataToServerForSocket("zhang");

        new Thread() {
            @Override
            public void run() {
                try {
                    String aa = sendDataToServerForSocket.getArticleData(result);
                    JSONObject jsonObject = new JSONObject(aa);
                    setTextViewData(jsonObject);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        /*LinearLayout ll = (LinearLayout) findViewById(R.id.lay);*/
        /*//把数据显示至屏幕
        for (int i = 0; i < 10; i++) {
            //1.集合中每有一条元素，就new一个textView
            TextView tv = new TextView(this);
            //2.把信息设置为文本框的内容
            tv.setText(i + "个");
            tv.setTextSize(20);
            tv.setTextColor(Color.BLACK);
            //3.把textView设置为线性布局的子节点
            ll.addView(tv);
        }*/

        /*TextView textView = new TextView(this);
        textView.setText("订单内容"+projectname+", 价格："+price);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        ll.addView(textView);*/

        ll = (LinearLayout) findViewById(R.id.lay);

        textView = new TextView(this);
    }

    public void setTextViewData(JSONObject result) {
        this.jsonObject = result;
        Message message = new Message();
        message.arg1=1;
        handler.sendMessage(message);
    }
}
