package com.studydemo.demo.network.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);

        //创建一个独立的线程，负责随即从socket中接受服务端发送过来的消息
        new ClientReaderThread(socket).start();

        //从socket获得一个字节输出流
        OutputStream os = socket.getOutputStream();
        //把低级的字节输出流包装成数据输出流
        DataOutputStream dos = new DataOutputStream(os);
        //开始写数据出去了\
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("请说");
            String msg = sc.nextLine();
            //一旦用户输入了exit就推出
            if ("exit".equals(msg)){
                System.out.println("推出了");
                dos.close();
                socket.close();
                break;
            }

            dos.writeUTF(msg);
            dos.flush();
        }


    }

}
