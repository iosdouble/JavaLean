package com.example.netty.second;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @ClassName ServerFixedLengthHandler
 * @Author nihui
 * @Date 2019/2/19 13:06
 * @Version 1.0
 * @Description TODO
 */
public class ServerFixedLengthHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        int length = buf.readableBytes();
//        if (length<3){
//
//        }else if (length>3){
//
//        }

        String message = msg.toString();
        System.out.println("from client :"+message);
        String line = "ok ";
        ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server exceptionCaught method run...");
        ctx.close();
    }
}
