package com.example.netty.frist;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName ServerHelloWorld
 * @Author nihui
 * @Date 2019/2/15 10:05
 * @Version 1.0
 * @Description TODO
 */
public class ServerHelloWorld {
    //创建线程组，监听客户端的请求
    private EventLoopGroup acceptorGroup = null;
    //处理客户端相关操作线程组，负责处理与客户端端的请求操作。
    private EventLoopGroup clientGroup = null;
    //服务器启动相关配置信息
    private ServerBootstrap bootstrap = null;

    public ServerHelloWorld() {
        init();
    }
    //初始化
    private void init() {
        //初始化线程组
        acceptorGroup = new NioEventLoopGroup();
        clientGroup = new NioEventLoopGroup();
        //初始化配置信息
        bootstrap = new ServerBootstrap();
        //绑定监听线程组
        bootstrap.group(acceptorGroup, clientGroup);
        //设置通信模式为NIO模式同步非阻塞
        bootstrap.channel(NioServerSocketChannel.class);
        //设定缓存区的大小,单位是字节
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        //SO_SNDBUF 表示发送缓冲区，SO_RCVBUF 表示接收缓存区，SO_KEEPALIVE 表示是否开启心跳检查，保证连接有效
        bootstrap.option(ChannelOption.SO_SNDBUF, 16 * 1024)
                .option(ChannelOption.SO_RCVBUF, 16 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);
    }
    /**
     * 监听处理逻辑
     * @param port 监听端口
     * @param acceptorHandlers 处理器
     * @return
     * @throws InterruptedException
     */
    public ChannelFuture doAccept(int port, final ChannelHandler... acceptorHandlers) throws InterruptedException {
        /**
         * childHandler 是服务端的BootStrap独有的方法是用于提供处理对象，提供处理对象可以一次性的增加若干个处理逻辑
         * 类似责任链模式的处理逻辑，也就是说你增加A 和B两个处理逻辑，在处理逻辑的时候会按照A 和 B 的顺序进行依次处理
         *
         * ChannelInitializer 用于提供处理器的一个模型对象，这个模型对象，其中定义了一个方法initChannel
         *
         * initChannel 这个方法适用于初始化处理逻辑责任链条的。可以保证服务端的BootStrap只初始化一次处理器，尽量提供处理器的重用，
         * 减少了反复创建处理器的操作
         */
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(acceptorHandlers);
            }
        });
        /**
         * bind 方法 用来绑定处理端口，ServerBootstrap可以绑定多个监听端口。多次调用bind方法即可
         *
         * sync 方法开始启动监听逻辑，返回ChannelFuture 返回结果是监听成功后的未来结果，可以使用
         * 这个ChannelFuture实现后续的服务器与客户端的交互所以要获取这个ChannelFuture对象
         */
        ChannelFuture future = bootstrap.bind(port).sync();
        //ChannelFuture future = bootstrap.bind(port).sync();
        //ChannelFuture future = bootstrap.bind(port).sync();
        return future;
    }
    /**
     * 回收方法
     * shutdownGracefully 是一个安全关闭的方法，可以保证不放弃任何一个以接收的客户端请求
     */
    public void release(){
        this.acceptorGroup.shutdownGracefully();
        this.clientGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        ChannelFuture future = null;
        ServerHelloWorld server = null;
        try{
            server = new ServerHelloWorld();
            future = server.doAccept(8081, new ServerHandler());
            System.out.println("server started.");
            //关闭连接，回收资源
            future.channel().closeFuture();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (null != future){
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (null!=server){
                server.release();
            }
        }
    }
}
