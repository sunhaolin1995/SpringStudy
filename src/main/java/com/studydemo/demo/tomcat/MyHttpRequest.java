package com.studydemo.demo.tomcat;

import java.io.IOException;
import java.io.InputStream;

public class MyHttpRequest {

    private InputStream inputStream;

    private String uri;

    public MyHttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    public void parse(){
        try {
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            String str = new String(bytes);
            parseUri(str);

            // System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseUri(String request) {
        int index1,index2;
        index1 =request.indexOf(' ');
        index2 = request.indexOf(' ',index1+1);
        uri=request.substring(index1+1,index2);
    }


    public String getUri(){
        return this.uri;
    }


}
