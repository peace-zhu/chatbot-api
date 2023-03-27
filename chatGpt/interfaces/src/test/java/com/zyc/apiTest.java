package com.zyc;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.UUID;

public class apiTest {
    @Test
    public void query_unsloved_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //httpClient主要是封装数据信息
        HttpGet get = new HttpGet("https://api.bilibili.com/x/v2/reply/add");
        get.addHeader("cookie","buvid3=0F0A4FB9-2E3B-B291-C916-31446400B7D720535infoc; b_nut=1669001120; i-wanna-go-back=-1; _uuid=EAC10B36D-6BFC-74105-AA43-3586694103A7E20944infoc; buvid_fp_plain=undefined; buvid_fp=6709983cf9a331c8263730db43c128a6; hit-new-style-dyn=0; hit-dyn-v2=1; nostalgia_conf=-1; rpdid=|(m)Y)YRmYk0J'uYYm||kYYR; LIVE_BUVID=AUTO6816699505640029; CURRENT_FNVAL=4048; buvid4=14E32654-AA67-4EF7-173C-BBE2E3013D6721630-022112111-ULU%2FjyJcyOnQF76D%2FZEOVw%3D%3D; DedeUserID=350519336; DedeUserID__ckMd5=242891bdacfe9e76; b_ut=5; header_theme_version=CLOSE; home_feed_column=5; fingerprint=7f338e99cb8a5f1abe50f35e5d612265; SESSDATA=8be9beae%2C1695431770%2C9a02d%2A31; bili_jct=605a2789ad249093546f918855b2c730; sid=51hwwpdx; share_source_origin=WEIXIN; bsource=share_source_weixinchat; bp_video_offset_350519336=777613248035815400; innersign=1; b_lsid=6FB1048E2_187219B7B73; CURRENT_QUALITY=80; PVID=2");
        get.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.bilibili.com/x/v2/reply/add");
        post.addHeader("cookie","buvid3=0F0A4FB9-2E3B-B291-C916-31446400B7D720535infoc; b_nut=1669001120; i-wanna-go-back=-1; _uuid=EAC10B36D-6BFC-74105-AA43-3586694103A7E20944infoc; buvid_fp_plain=undefined; buvid_fp=6709983cf9a331c8263730db43c128a6; hit-new-style-dyn=0; hit-dyn-v2=1; nostalgia_conf=-1; rpdid=|(m)Y)YRmYk0J'uYYm||kYYR; LIVE_BUVID=AUTO6816699505640029; CURRENT_FNVAL=4048; buvid4=14E32654-AA67-4EF7-173C-BBE2E3013D6721630-022112111-ULU%2FjyJcyOnQF76D%2FZEOVw%3D%3D; DedeUserID=350519336; DedeUserID__ckMd5=242891bdacfe9e76; b_ut=5; header_theme_version=CLOSE; home_feed_column=5; fingerprint=7f338e99cb8a5f1abe50f35e5d612265; SESSDATA=8be9beae%2C1695431770%2C9a02d%2A31; bili_jct=605a2789ad249093546f918855b2c730; sid=51hwwpdx; share_source_origin=WEIXIN; bsource=share_source_weixinchat; bp_video_offset_350519336=777613248035815400; innersign=1; b_lsid=6FB1048E2_187219B7B73; CURRENT_QUALITY=80; PVID=2");
        post.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//        URLEncode%E8%BD%AC%E6%B1%89%E5%AD%97%20%20%20%20%20%20%20%20
        String paramJson ="oid=564983742&type=1&root=146160088704&parent=146160088704&message=%E5%9B%9E%E5%A4%8D+%40%E5%AE%9E%E7%8E%B0%E7%9F%BF%E6%B3%89%E6%B0%B4%E8%87%AA%E7%94%B1+%3Atest&plat=1&jsonp=jsonp&code=&csrf=605a2789ad249093546f918855b2c730";

        StringEntity stringEntity = new StringEntity(paramJson,ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
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
    @Test
    public void ChatBotSend() throws Exception {
        //创建ds对象
        DatagramSocket ds = new DatagramSocket();
        //打包数据
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            System.out.println("请输入你要说的话：");
            str = sc.nextLine();
            if ("886".equals(str)) {
                break;
            }
        }
        byte[] bytes = str.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 10086;
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);
        ds.send(dp);
        ds.close();
    }
    @Test
    public void ChatBotReceive() throws Exception{
        //创建ds对象
        DatagramSocket ds = new DatagramSocket(10086);
        //接受数据包
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        while (true) {
            ds.receive(dp);
            //解析数据包
            byte[] data =dp.getData();
            int len = dp.getLength();
            String ip = dp.getAddress().getHostAddress();
            //打印数据
            System.out.println("查看ip:"+ip+new String(data,0,len));
        }
    }
    @Test
    public void Test(){
        String paramJson ="oid=564983742&type=1&root=146160088704&parent=146160088704&message=%E5%9B%9E%E5%A4%8D+%40%E5%AE%9E%E7%8E%B0%E7%9F%BF%E6%B3%89%E6%B0%B4%E8%87%AA%E7%94%B1+%3Atest&plat=1&jsonp=jsonp&code=&csrf=605a2789ad249093546f918855b2c730";
        StringEntity entity = new StringEntity(paramJson, ContentType.create("application/x-www-form-urlencoded", "UTF-8"));
        String keyWord = URLDecoder.decode("%E5%9B%9E%E5%A4%8D+%40%E5%AE%9E%E7%8E%B0%E7%9F%BF%E6%B3%89%E6%B0%B4%E8%87%AA%E7%94%B1+%3Atest");
        System.out.println(keyWord);
    }

}
