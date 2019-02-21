package com.example.netty.delimiter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @ClassName ServerDelimiter
 * @Author nihui
 * @Date 2019/2/20 12:53
 * @Version 1.0
 * @Description TODO
 */
public class ServerDelimiter {
    private EventLoopGroup acceptorGroup = null;

    private EventLoopGroup clientGroup = null;

    private ServerBootstrap bootstrap = null;
    
    public ServerDelimiter(){
        init();
    }

    private void init() {
        acceptorGroup = new NioEventLoopGroup();

        clientGroup = new NioEventLoopGroup();

        bootstrap = new ServerBootstrap();

        bootstrap.group(acceptorGroup,clientGroup);

        bootstrap.channel(NioServerSocketChannel.class);

        bootstrap.option(ChannelOption.SO_BACKLOG,1024);

        bootstrap.option(ChannelOption.SO_SNDBUF,16*1024)
                .option(ChannelOption.SO_RCVBUF,16*1024)
                .option(ChannelOption.SO_KEEPALIVE,true);

    }

    public ChannelFuture doAccept(int port) throws InterruptedException {
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ByteBuf delimiter = Unpooled.copiedBuffer("$E$".getBytes());
                ChannelHandler[] acceptorHandlers = new ChannelHandler[3];
                acceptorHandlers[0] = new DelimiterBasedFrameDecoder(1024,delimiter);
                acceptorHandlers[1] = new StringDecoder(Charset.forName("UTF-8"));
                acceptorHandlers[2] = new ServerDelimiterHandler();
                ch.pipeline().addLast(acceptorHandlers);
            }
        });
        ChannelFuture future = bootstrap.bind(port).sync();
        return future;
    }
    public void release(){
        acceptorGroup.shutdownGracefully();
        clientGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        ChannelFuture future = null;
        ServerDelimiter server = null;

        try{
            server = new ServerDelimiter();

            future =server.doAccept(8081);
            System.out.println("server started!");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (null!=future){
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
