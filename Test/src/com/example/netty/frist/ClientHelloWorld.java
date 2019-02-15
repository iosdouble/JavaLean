package com.example.netty.frist;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ClientHelloWorld
 * @Author nihui
 * @Date 2019/2/15 10:57
 * @Version 1.0
 * @Description 客户端代码
 *
 * 因为客户端是发起请求的所以说不需要监听
 * 只需要定义唯一的一个线程组即可
 */
public class ClientHelloWorld {
    //处理请求线程组
    private EventLoopGroup group = null;
    //服务启动相关配置信息
    private Bootstrap bootstrap = null;
    public ClientHelloWorld(){
        init();
    }
    private void init(){
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        //定义线程组
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
    }
    public ChannelFuture doRequest(String host, int port, final ChannelHandler ... handlers) throws InterruptedException {
        this.bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            /**
             * 客户端的Handler没有childHandler方法，只有Handler方法
             * 这个方法与服务器的方法是类似的。
             * 客户端必须绑定处理器，也就说必须调用Handler方法
             * @param ch
             * @throws Exception
             */
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(handlers);
            }
        });
        //建立连接
        ChannelFuture future = this.bootstrap.connect(host,port).sync();
        return future;
    }
    public void release(){
        this.group.shutdownGracefully();
    }
    public static void main(String[] args) {
        ClientHelloWorld client = null;
        ChannelFuture future = null;
        try{
            client = new ClientHelloWorld();
            future = client.doRequest("localhost",8081,new ClientHandler());
            Scanner scanner = null;
            while (true){
                scanner = new Scanner(System.in);
                System.out.println("enter message send to server (enter exit close client)");
                String line = scanner.nextLine();
                if ("exit".equals(line)){
                    future.channel().writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8"))).addListener(ChannelFutureListener.CLOSE);
                    break;
                }
                future.channel().writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null!=client){
                client.release();
            }
            if (null!=future){
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
