package com.studydemo.demo.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        //创建客户端对象（发送信息出去的人）
        DatagramSocket socket =new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        int i =0;
        //创建数据包发送对象
        while (true){
            System.out.println("请说");
            String msg = sc.nextLine();

            if ("exit".equals(msg)){
                System.out.println("退出了");
                socket.close();
                break;
            }
            byte[] bytes = ("我是客户端"+i).getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length,
                    InetAddress.getLocalHost(),6666);
            //开始正式发送数据
            socket.send(packet);
            i++;
        }


        /*System.out.println("客户端发送完毕");
        socket.close();*/

    }



}
