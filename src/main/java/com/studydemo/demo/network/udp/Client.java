package com.studydemo.demo.network.udp;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {


    public static void main(String[] args) throws IOException {
        //创建客户端对象（接收消息的人）
        DatagramSocket socket = new DatagramSocket();

        byte[] bytes = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

        while (true){

            socket.receive(packet);
            int length = packet.getLength();

            String rs = new String(bytes,0,length);
            System.out.println(rs);

        }

    }


}
