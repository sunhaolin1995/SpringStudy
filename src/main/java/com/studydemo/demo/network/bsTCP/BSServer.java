package com.studydemo.demo.network.bsTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class BSServer {

    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动成功");
        //创建serversocket对象
        ServerSocket serverSocket = new ServerSocket(8888);
        //使用serversocket对象调用一个accept方法，等待客户端的链接请求


        //创建一个线程池，负责处理通信管道的任务
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16 * 2, 16 * 2, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(8), Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy());



        while (true){
            Socket socket = serverSocket.accept();
            //从socket管道中获得一个字节输入流

            System.out.println(socket.getRemoteSocketAddress()+"上线了");

            threadPoolExecutor.execute(new serverReaderSocketRunnable(socket));
        }
    }

}
