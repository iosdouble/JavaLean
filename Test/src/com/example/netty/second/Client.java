package com.example.netty.second;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Client
 * @Author nihui
 * @Date 2019/2/19 13:18
 * @Version 1.0
 * @Description TODO
 */
public class Client {

    private EventLoopGroup group = null;

    private Bootstrap bootstrap = null;

    public Client(){
        init();
    }

    private void init(){
        group = new NioEventLoopGroup();

        bootstrap = new Bootstrap();

        bootstrap.group(group);

        bootstrap.channel(NioSocketChannel.class);
    }
    public ChannelFuture doRequest(String host,int port) throws InterruptedException {
        this.bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ByteBuf delimiter = Unpooled.copiedBuffer("$E$".getBytes());
                ChannelHandler[] handlers = new ChannelHandler[3];
                handlers[0] = new FixedLengthFrameDecoder(3);
                handlers[1] = new StringDecoder(Charset.forName("UTF-8"));
                handlers[2] = new ClientFixedLenghtHandler();

                ch.pipeline().addLast(handlers);
            }
        });
        ChannelFuture future = this.bootstrap.connect(host,port).sync();
        return future;
    }

    public void release(){
        this.group.shutdownGracefully();
    }

    public static void main(String[] args) {
        Client client = null;
        ChannelFuture future = null;

        try{
            client = new Client();
            future = client.doRequest("localhost",8081);
            Scanner scanner = null;
            while (true){
                scanner = new Scanner(System.in);
                System.out.println("enter message send to server > ");
                String line = scanner.nextLine();
                future.channel().writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null!=future){
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (null!=client){
                client.release();
            }
        }
    }
}
