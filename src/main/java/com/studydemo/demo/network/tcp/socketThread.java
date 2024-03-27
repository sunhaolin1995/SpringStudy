package com.studydemo.demo.network.tcp;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

import static com.studydemo.demo.network.tcp.TCPServer.onlineSocket;

public class socketThread extends Thread{

    private Socket socket;

    public socketThread(Socket socket){
        this.socket=socket;
    }

    @SneakyThrows
    @Override
    public void run() {
        //从socket管道中获得一个字节输入流
        InputStream is = socket.getInputStream();
        //把原始的字节输入流包装成数据输入流
        DataInputStream dis = new DataInputStream(is);

        while (true) {
            //使用数据输入流读取客户端发送来的消息
            try {
                String rs = dis.readUTF();
                System.out.println(rs);
                System.out.println(socket.getRemoteSocketAddress());
                sendMsgToAll(rs);
            } catch (Exception e) {
                System.out.println(socket.getRemoteSocketAddress() + "离线了");
                dis.close();
                socket.close();
                onlineSocket.remove(socket);
                break;
            }

        }
    }

    private void sendMsgToAll(String rs) throws IOException {
        //发送给全部在线的socket
        for (Socket onlineSocket : onlineSocket) {
            OutputStream os = onlineSocket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(rs);
            dos.flush();
        }
    }


}
