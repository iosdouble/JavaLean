package com.example.netty.frist;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @ClassName ServerHandler
 * @Author nihui
 * @Date 2019/2/15 10:36
 * @Version 1.0
 * @Description
 *
 * @ChannelHandler.Sharable 这个注解表示当前是一个可以分享的处理器，，服务注册此Handler，可以分享给多个客户端使用
 * 如果不使用这个注解的话，每次客户端请求时，必须为客户端重新创建一个新的处理器Handler对象
 *
 * bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
 *             @Override
 *             protected void initChannel(SocketChannel ch) throws Exception {
 *                 ch.pipeline().addLast(new XXXHandler（）);
 *             }
 *         });
 * 建议自己开发的时候就会使的Handler是可共享的
 *
 * 如果Handler是一个可分享的，一定避免定义一个可以写的实例变量。不安全
 *
 */
@ChannelHandler.Sharable
public class ServerHandler extends ChannelHandlerAdapter {
    /**
     * 业务处理逻辑  用于处理读取数据请求的逻辑。它里面的方法和参数如下
     * @param ctx 上下文对象，其中包含于客户端建立连接的所有资源，比如说对应的Channel
     * @param msg 读取到的数据，默认类型是bytebuf 这个ByteBuf 是对ByteBuffer的一个封装。简化了操作
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //强制类型转换
        ByteBuf readBuffer = (ByteBuf) msg;
        // 创建一个字节数组，用于保存缓存中的数据。readableBytes 在原生的ByteBuffer也有同样的功能的方法
        byte[] tempDatas = new byte[readBuffer.readableBytes()];
        //读取到对应的数据 可以直接读取，这个不需要考虑复位的问题，
        readBuffer.readBytes(tempDatas);
        String message = new String(tempDatas,"UTF-8");
        System.out.println("from client :"+message);
        if ("exit".equals(message)){
            //如果客户端断开连接，则关闭上下文
            ctx.close();
            return;
        }
        String line = "server message to client!";
        //写操作自动释放缓存，避免内存溢出的问题。
        ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));

        /**
         * 如果调用的是write方法，不会刷新缓存，缓存中的数据不会发送到客户端，必须调用flush方法进行强制刷出
         *  ctx.write(msg);
         *  ctx.flush();
         */
    }
    /**
     * 异常处理逻辑
     * ChannelHandlerContext关闭代表当前与客户端的连接处理逻辑，当客户端异常退出的时候这个异常也会执行
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server exceptionCaught method run …… ");
        ctx.close();
    }
}
