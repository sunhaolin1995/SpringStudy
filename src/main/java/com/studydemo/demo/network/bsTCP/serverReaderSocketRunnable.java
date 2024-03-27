package com.studydemo.demo.network.bsTCP;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

import static com.studydemo.demo.network.tcp.TCPServer.onlineSocket;

public class serverReaderSocketRunnable implements Runnable {

    private Socket socket;

    public serverReaderSocketRunnable(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        //立即响应一个网页内容
        try {
            OutputStream os= socket.getOutputStream();
            PrintStream ps= new PrintStream(os);
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-type:text/html;charSet=UTF-8");
            ps.println();//必须换行
            ps.println("<div>hello</div>");
            ps.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
