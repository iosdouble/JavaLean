package com.example.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @ClassName NIOClient
 * @Author nihui
 * @Date 2019/2/12 16:15
 * @Version 1.0
 * @Description TODO
 */
public class NIOClient {
    public static void main(String[] args) {
        InetSocketAddress remote = new InetSocketAddress("localhost",8081);
        SocketChannel channel = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try{
            //开启通道
            channel = SocketChannel.open();
            //连接远程服务器
            channel.connect(remote);
            Scanner reader = new Scanner(System.in);
            while (true){
                System.out.println("put message for send to server > ");
                String line = reader.nextLine();
                if (line.equals("exit")){
                    break;
                }
                //将控制台输入的数据写入到缓存中
                buffer.put(line.getBytes("UTF-8"));
                //重置游标
                buffer.flip();
                //将数据发送到客户端
                channel.write(buffer);
                //清空缓存数据
                buffer.clear();


                int readLength = channel.read(buffer);
                if (readLength==-1){
                    break;
                }
                //重置游标
                buffer.flip();
                byte[] datas = new byte[buffer.remaining()];
                //读取数据到字节数组
                buffer.get(datas);

                System.out.println("from server :"+ new String(datas,"UTF-8"));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=channel){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
