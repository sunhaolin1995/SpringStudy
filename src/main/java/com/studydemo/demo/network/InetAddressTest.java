package com.studydemo.demo.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*
掌握inetAddress类的使用

 */
public class InetAddressTest {

    public static void main(String[] args) throws IOException {
        //获取本机ip地址对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getHostAddress());

        //获取指定ip或者域名的ip地址

        InetAddress allByName = InetAddress.getByName("www.baidu.com");
        System.out.println(allByName.getHostName());
        System.out.println(allByName.getHostAddress());
        byte[] address = allByName.getAddress();
        for (int i = 0; i < address.length; i++) {
            System.out.println(address[i]);
        }

        System.out.println(allByName.isReachable(600));
    }

}
