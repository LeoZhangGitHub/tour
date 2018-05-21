package com.example.administrator.tour.homepage.reserveAcitivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.tour.R;
import com.example.administrator.tour.SendResultToServer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class SiteReserveActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_reserve);

        final TextView site_tilte = (TextView) findViewById(R.id.site_title);
        final TextView site_price = (TextView) findViewById(R.id.site_price);

        Button button = (Button) findViewById(R.id.id_site_button_reserve);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //创建JSON对象
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("doWhat", "siteorder");
                    jsonObject.put("projectname", site_tilte.getText());
                    jsonObject.put("price", site_price.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String result = jsonObject.toString();
                SendResultToServer.getInstance().commit(result);
            }
        });
    }
}
