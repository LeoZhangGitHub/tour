package com.example.administrator.tour;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


/**
 * Created by Administrator on 2018/3/25.
 * Date:2018.03.25
 */
public class SendDataToServerForSocket {

    private String username;
    private String password;

    SendDataToServerForSocket(String username, String password) {
        this.password = password;
        this.username = username;

        try {
            SendDataToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendDataToServer() throws IOException {
            //1.创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("192.168.25.1", 12345);
            //2.获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
            //获取客户端的IP地址
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();
            pw.write("客户端：~" + ip + "~ 接入服务器！！");
            pw.write("username: " + username + " password: " + password);
            pw.flush();
            socket.shutdownOutput();//关闭输出流
            socket.close();
        }
}
