package com.studydemo.demo.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHttpServer {


    //端口
    private int port =8080;

    //接受请求的方法
    public void receiving(){
        //创建socket服务
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            //循环接受请求
            while (true){
                //获取到连接对象
                Socket socket = serverSocket.accept();
                //System.out.println(socket);
                //获取连接对象的输入流
                InputStream inputStream = socket.getInputStream();
               // System.out.println(inputStream);
                //创建Request
                MyHttpRequest request = new MyHttpRequest(inputStream);
                //解析请求
                request.parse();
                //创建response
                OutputStream outputStream = socket.getOutputStream();
                MyHttpResponse response = new MyHttpResponse(outputStream);
                //进行响应
                response.sendDirect(request.getUri());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
