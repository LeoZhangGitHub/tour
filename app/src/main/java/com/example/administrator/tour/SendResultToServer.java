package com.example.administrator.tour;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/5/19/019.
 */

public class SendResultToServer {
    public static SendResultToServer instance;

    public static SendResultToServer getInstance(){

        if(instance == null){

            instance = new SendResultToServer();

        }

        return instance;

    }

    public void commit(final String result) {
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

}
