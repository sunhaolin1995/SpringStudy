package com.studydemo.demo.tomcat;

public class Test {


    public static void main(String[] args) {
        System.out.println("Server startup successfully");
        MyHttpServer server = new MyHttpServer();
        server.receiving();
    }
}
