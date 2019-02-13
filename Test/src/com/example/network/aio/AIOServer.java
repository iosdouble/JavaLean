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
    //线程池，提高服务端效率
    private ExecutorService service;
    //线程组
   // private AsynchronousChannelGroup group;
    //服务端通道,针对服务器端定义的通道
    private AsynchronousServerSocketChannel serverSocketChannel;

    public AIOServer(int port){
        init(8081);
    }

    private void init(int port) {

        try {
            System.out.println("server starting at port ："+port+"....");
            //定长线程池
            service = Executors.newFixedThreadPool(4);
            //使用线程组
            /*
            group = AsynchronousChannelGroup.withThreadPool(service);
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            */
            //开启服务端通道，通过静态方法创建的。
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            //绑定监听端口，服务器启动成功，但是还没有开始监听请求
            serverSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("server started.");
            //开始监听
            /**
             * com.example.network.aio.AIOServer
             * 描述：AIOServer
             * 时间：2019/2/13
             * 作者：nihui
             * 说明：
             * accept(A attachment,CompletionHandler<AsynchronousSocketChannel,? super A> handler)
             *
             * 在AIO开发中监听是一个类似递归的监听操作，每次监听到客户端请求后，都需要处理逻辑开启下一次监听
             * 下一次监听需要服务器的资源继续支持。
             *
             */
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
