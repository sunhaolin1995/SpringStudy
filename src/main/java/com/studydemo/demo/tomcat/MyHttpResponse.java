package com.studydemo.demo.tomcat;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyHttpResponse {

        private OutputStream outputStream;

        public MyHttpResponse(OutputStream outputStream){
            this.outputStream=outputStream;
        }

        public void sendDirect(String uri) throws IOException {
            //判断uri是否存在，如果不存在返回404，存在返回目标资源数据
            //E:\ideaFile\SpringStudy E:\ideaFile\SpringStudy\src\main\java\com\studydemo\demo\tomcat\WebContext
           // System.out.println(System.getProperty("user.dir")+"\\src\\main\\java\\com\\studydemo\\demo\\tomcat\\WebContext"+uri);
            File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\studydemo\\demo\\tomcat\\WebContext"+uri);
            //System.out.println(file.exists());
            if (file.exists()){
                //返回目标资源
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                fileInputStream.read(bytes);
                String res =new String(bytes);
                String response = getResponseMessage("200",res);
                this.outputStream.write(response.getBytes());

            }else {
               String error =getResponseMessage("404","!!!!!!!!!!!!");
                this.outputStream.write(error.getBytes());
            }

        }
        public String getResponseMessage(String code,String message){
            return  "HTTP/1.1 " +code+"\r\n"
                    + "Content-type:text/html\r\n"
                    +"Content-Length: "+message.length()
                    +"\r\n"
                    +"\r\n"+message;

        }


}
