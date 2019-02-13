package com.example.network.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AIOServer
 * @Author nihui
 * @Date 2019/2/12 17:16
 * @Version 1.0
 * @Description TODO
 */
public class AIOServer {
    //线程池
    private ExecutorService service;
    //线程组
   // private AsynchronousChannelGroup group;
    //服务端通道
    private AsynchronousServerSocketChannel serverSocketChannel;

    public AIOServer(int port){
        init(8081);
    }

    private void init(int port) {

        try {
            System.out.println("server starting at port ："+port+"....");
            service = Executors.newFixedThreadPool(4);
            //使用线程组
            /*
            group = AsynchronousChannelGroup.withThreadPool(service);
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            */
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("server started.");
            serverSocketChannel.accept(this,new AIOServerHandler());
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AIOServer(8081);
    }

    public ExecutorService getService() {
        return service;
    }

    public void setService(ExecutorService service) {
        this.service = service;
    }

    public AsynchronousServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public void setServerSocketChannel(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }
}
