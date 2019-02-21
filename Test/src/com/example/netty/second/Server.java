package com.example.netty.second;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @ClassName Server
 * @Author nihui
 * @Date 2019/2/19 12:56
 * @Version 1.0
 * @Description TODO
 */
public class Server {
    private EventLoopGroup acceptorGroup = null;
    private EventLoopGroup clientGroup = null;

    private ServerBootstrap bootstrap = null;

    public Server(){
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
                //定长处理器《通过构造函数设置消息长度，发送的消息长度不全的时候使用空格补全
                acceptorHandlers[0] = new FixedLengthFrameDecoder(3);
                //字符解码器Handler，会自动处理channelRead方法的msg参数，将ByteBuf类型的数据转换为字符串。
                acceptorHandlers[1] = new StringDecoder(Charset.forName("UTF-8"));
                acceptorHandlers[2] = new ServerFixedLengthHandler();
                ch.pipeline().addLast(acceptorHandlers);
            }
        });
        ChannelFuture future = bootstrap.bind(port).sync();
        return future;
    }

    public void release(){
        this.acceptorGroup.shutdownGracefully();
        this.clientGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        ChannelFuture future = null;
        Server server = null;

        try {

            server = new Server();
            future = server.doAccept(8081);
            System.out.println("server started.");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (null!=future){
                try{
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
