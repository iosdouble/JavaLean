package com.example.netty.nettyoom;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @ClassName NettyOOM
 * @Author nihui
 * @Date 2019/5/10 9:14
 * @Version 1.0
 * @Description TODO
 */
public class NettyOOM {

    //正确连接方式
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new LoggingHandler());
                    }
                });

        for (int i = 0; i <100 ; i++) {
            bootstrap.connect("127.0.0.1",8080).sync();
        }

        //错误释放方式
        for (int i = 0; i < 100; i++) {
            ChannelFuture f = bootstrap.connect("127.0.0.1",8080).sync();
            f.channel().closeFuture().addListener((r)->{
                group.shutdownGracefully();
            });
        }

        //正确释放
        ChannelFuture f = bootstrap.connect("127.0.0.1",8080).sync();
        f.channel().closeFuture().sync();

    }
    //错误连接方式
    static void initClientPool(int poolSize) throws InterruptedException {
        for (int i = 0; i < poolSize; i++) {
            EventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new LoggingHandler());
                        }
                    });
            ChannelFuture f = bootstrap.connect("127.0.0.1",8080).sync();
            f.channel().closeFuture().addListener((r)->{
               group.shutdownGracefully();
            });
        }
    }
}
