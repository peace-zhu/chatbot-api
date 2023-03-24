package com.zyc;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class apiTest {
    @Test
    public void query_unsloved_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //httpClient主要是封装数据信息
        HttpGet get = new HttpGet("https://www.baidu.com/");
        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void TestInet() throws Exception {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        String hostName = address.getHostName();
        System.out.println(hostName);
    }
    @Test
    public void SendMessageDemo() throws Exception{
        //创建DatagramSocket
        DatagramSocket ds = new DatagramSocket();
        //打包数据
        String str = "你好吗？";
        byte[] bytes = str.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 10086;
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length,address,port);
        //发送数据
        ds.send(dp);
        //释放资源
        ds.close();
    }
    @Test
    public void receiveMessage() throws Exception{
        //创建ds对象，一定要绑定端口
        DatagramSocket ds = new DatagramSocket(10086);
        //接收数据包
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);
        ds.receive(dp);
        //解析数据包
        byte[] data = dp.getData();
        int len = dp.getLength();
        InetAddress address = dp.getAddress();
        int port = dp.getPort();
        System.out.println("接收到的数据shi :  "+new String(data,0,len));
        System.out.println("该数据是从"+address+"asd "+port);
        //关闭资源
        ds.close();
    }
}
