package com.studydemo.demo.network.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {
    public volatile static List<Socket> onlineSocket = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        System.out.println("服务端启动成功");
        //创建serversocket对象
        ServerSocket serverSocket = new ServerSocket(8888);
        //使用serversocket对象调用一个accept方法，等待客户端的链接请求

       while (true){
           Socket socket = serverSocket.accept();
           //从socket管道中获得一个字节输入流

           System.out.println(socket.getRemoteSocketAddress()+"上线了");
           onlineSocket.add(socket);
           new socketThread(socket).start();
       }


    }
}
