package com.example.administrator.tour;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


/**
 * Created by Administrator on 2018/3/25.
 * Date:2018.03.25
 */
public class SendDataToServerForSocket {

    private String username="";

    //定义相关变量,完成初始化

    private static final String HOST = "192.168.138.84";
    private static final int PORT = 12345;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String content = "";
    private StringBuilder sb = null;

    SendDataToServerForSocket(String username) {
        this.username = username;
    }


    public void SendDataToServer() {

        sb = new StringBuilder();

        //当程序一开始运行的时候就实例化Socket对象,与服务端进行连接,获取输入输出流
        //因为4.0以后不能再主线程中进行网络操作,所以需要另外开辟一个线程
        new Thread() {
            public void run() {
                try {
                    socket = new Socket(HOST, PORT);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())), true);

                    if (socket.isConnected()) {
                        if (!socket.isOutputShutdown()) {

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        String msg;
                                        try {
                                            msg = username.toString();
                                        } catch (NullPointerException e) {
                                            msg = "no user".toString();
                                        }
                                        out.println(msg);
                                        if ((content = in.readLine()) != null) {
                                            content += "\n";
                                            sb.append(content);
                                            System.out.println(sb.toString());
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }).start();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /*//从服务器获取电话号码
    public String getUserPhoneNumber(String username) throws IOException{
        socket = new Socket(HOST, PORT);
        String msg = username;
        String phoneNumber =null;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream())), true);
        if (socket.isConnected()) {
            if (!socket.isOutputShutdown()) {
                out.println(msg);
                try {
                    phoneNumber = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return phoneNumber;

    }*/
    /*//从服务器获取电话号码
    public String getUserPhoneNumber(String username) throws IOException{
        String msg = username;
        String phoneNumber =null;
        if (socket.isConnected()) {
            if (!socket.isOutputShutdown()) {
                out.println(msg);
                try {
                    phoneNumber = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return phoneNumber;

    }*/
    public String getArticleData(String ID) throws IOException{
        socket = new Socket(HOST, PORT);
        String phoneNumber =null;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream())), true);
        String msg = ID;
        String articleData =null;
        if (socket.isConnected()) {
            if (!socket.isOutputShutdown()) {
                out.println(msg);
                try {
                    articleData = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return articleData;

    }

}
