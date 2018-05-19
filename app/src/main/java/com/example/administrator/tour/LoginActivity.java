package com.example.administrator.tour;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Administrator on 2018/3/25/025.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText editAccount,editPassword;
    private Button loginButton;

    private String name;
    private String pwd;

    Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SendDataToServerForGet.SEND_SUCCESS:
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("username", name);
                    intent.setClass(LoginActivity.this, MainActivity.class);

                    //创建JSON对象
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("doWhat", "username");
                        jsonObject.put("username", name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String result = jsonObject.toString();
                    SendResultToServer.getInstance().commit(result);
                    startActivity(intent);
                    break;
                case SendDataToServerForGet.SEND_FAIL:
                    Toast.makeText(LoginActivity.this, "登陆失败，请检查用户名或密码！", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editAccount=(EditText)findViewById(R.id.loginAccount_id);
        editPassword=(EditText)findViewById(R.id.password_id);
        loginButton=(Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                if (editAccount.equals("")||editPassword.equals("")) {
                }else {
                    new Thread() {
                        @Override
                        public void run() {
                                name=editAccount.getText().toString();
                                pwd=editPassword.getText().toString();
                            // new SendDataToServerForSocket(name, pwd);
                            new SendDataToServerForGet(handler).SendDataToServerForGet(name, pwd);

                        }
                    }.start();

                }
            }
        });
    }

}

