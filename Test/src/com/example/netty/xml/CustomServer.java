package com.example.netty.xml;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


public class CustomServer {

    //一次读取中最大长度
    private static final int MAX_FRAME_LENGTH = 1024 * 1024;

    //计算起始位置
    private static final int LENGTH_FIELD_OFFSET = 0;
    //报文长度最大表示
    private static final int LENGTH_FIELD_LENGTH = 2;
    //修改值
    private static final int LENGTH_ADJUSTMENT = 0;
    //解析的时候需要跳过的字节数
    private static final int INITIAL_BYTES_TO_STRIP = 2;

    private ServerBootstrap sbs;
    private EventLoopGroup workerGroup;
    private EventLoopGroup bossGroup;
    private int port;

    public CustomServer(int port) {
        this.port = port;
        init();
    }

    private void init() {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        sbs = new ServerBootstrap();
        sbs.group(bossGroup, workerGroup);
        sbs.channel(NioServerSocketChannel.class);
        sbs.option(ChannelOption.SO_BACKLOG, 1024);

        sbs.option(ChannelOption.SO_SNDBUF, 2*1024 * 1024)
                .option(ChannelOption.SO_RCVBUF, 2*1024 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);
    }


    public void start() {
        try {
            sbs.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new XmlDecoder(MAX_FRAME_LENGTH,LENGTH_FIELD_OFFSET,LENGTH_FIELD_LENGTH,
                            LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP, false));
                    ch.pipeline().addLast(new CustomServerHandler());
                    ch.pipeline().addLast(new StringDecoder());
                }

                ;
            });
            // 绑定端口，开始接收进来的连接
            ChannelFuture future = sbs.bind(port).sync();
            System.out.println("Server start listen at " + port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }



//    public void start() {
//        try {
//            sbs.childHandler(new ChannelInitializer<SocketChannel>() {
//                protected void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,2));
//                    ch.pipeline().addLast(new CustomServerHandler());
//                }
//            });
//            // 绑定端口，开始接收进来的连接
//            ChannelFuture future = sbs.bind(port).sync();
//            System.out.println("Server start listen at " + port);
//            future.channel().closeFuture().sync();
//        } catch (Exception e) {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//    }

    public static void main(String[] args) throws Exception {
        new CustomServer(10001).start();
    }
}

