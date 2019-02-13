package com.example.network.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName AIOClient
 * @Author nihui
 * @Date 2019/2/12 17:49
 * @Version 1.0
 * @Description TODO
 */
public class AIOClient {
    private AsynchronousSocketChannel channel;

    public AIOClient(String host,int port){
        init(host,port);
    }

    private void init(String host, int port) {
        try {
            channel = AsynchronousSocketChannel.open();
            channel.connect(new InetSocketAddress(host,port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String line){
        try{
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(line.getBytes("UTF-8"));
            buffer.flip();
            channel.write(buffer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try{
            channel.read(buffer).get();
            buffer.flip();
            System.out.println("from server : "+ new String(buffer.array(),"UTF-8"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
       AIOClient client =  new AIOClient("localhost",8081);
       try{
           System.out.println("enter message send to server > ");
           Scanner scanner = new Scanner(System.in);
           String line = scanner.nextLine();
           client.write(line);
           client.read();
       }finally {
           client.doDestory();
       }
    }

    private void doDestory() {
        if (channel!=null){
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
