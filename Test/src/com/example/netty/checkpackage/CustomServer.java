package com.example.netty.checkpackage;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;



public class CustomServer {

    //一次读取中最大长度
    private static final int MAX_FRAME_LENGTH = 1024 * 1024;
    //报文长度最大表示
    private static final int LENGTH_FIELD_LENGTH = 4;
    //计算起始位置
    private static final int LENGTH_FIELD_OFFSET = 2;
    //修改值
    private static final int LENGTH_ADJUSTMENT = 0;
    //解析的时候需要跳过的字节数
    private static final int INITIAL_BYTES_TO_STRIP = 0;

    private ServerBootstrap sbs;
    private EventLoopGroup workerGroup;
    private EventLoopGroup bossGroup;
    private int port;

    public CustomServer(int port) {
        this.port = port;
        init();
    }

    private void init() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        sbs = new ServerBootstrap();
        sbs.group(bossGroup, workerGroup);
        sbs.channel(NioServerSocketChannel.class);
        sbs.option(ChannelOption.SO_BACKLOG, 1024);

        sbs.option(ChannelOption.SO_SNDBUF, 16 * 1024)
                .option(ChannelOption.SO_RCVBUF, 16 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);
    }

    public void start() {
        try {
            sbs.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new CustomDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_LENGTH,
                            LENGTH_FIELD_OFFSET, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP, false));
                    ch.pipeline().addLast(new CustomServerHandler());
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
        }
    }

    public static void main(String[] args) throws Exception {
        new CustomServer(10001).start();
    }
}

