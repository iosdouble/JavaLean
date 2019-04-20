package com.example.netty.second;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
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
               // ByteBuf delimiter = Unpooled.copiedBuffer("$E$".getBytes());
                ChannelHandler[] acceptorHandlers = new ChannelHandler[3];
                //定长处理器《通过构造函数设置消息长度，发送的消息长度不全的时候使用空格补全
                //new LengthFieldBasedFrameDecoder()
                /**
                 * maxFrameLength：单个包最大的长度，这个值根据实际场景而定，但是其他包可能比较大。
                 *
                 * lengthFieldOffset：表示数据长度字段开始的偏移量，
                 *                    比如上面的协议，lengthFieldOffset应该取值为5，
                 *                    因为数据长度之前有2个字节的包头，1个字节的功能ID，
                 *                    2个字节的设备ID,一共为5。
                 *
                 * lengthFieldLength：数据长度字段的所占的字节数，上面的协议中写的是2个字节，所以取值为2
                 *
                 * lengthAdjustment：这里取值为10=7(系统时间) + 1（校验码）+ 2 (包尾)，如果这个值取值为0，
                 *                      试想一下，解码器跟数据长度字段的取值（这里数据长度内容肯定是1），
                 *                      只向后取一个字节，肯定不对。
                 *                      （lengthAdjustment + 数据长度取值 = 数据长度字段之后剩下包的字节数）
                 * initialBytesToStrip：表示从整个包第一个字节开始，向后忽略的字节数，我设置为0，
                 *                      本来可以忽略掉包头的两个字节（即设置为2），但是，实际项目中，需要校验包头取值是否为AA55，来判断包的合法性
                 */
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
