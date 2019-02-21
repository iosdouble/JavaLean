package com.example.netty.delimiter;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @ClassName ServerDelimiterHandler
 * @Author nihui
 * @Date 2019/2/20 13:01
 * @Version 1.0
 * @Description TODO
 */
public class ServerDelimiterHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = msg.toString();
        System.out.println("from client ："+message);
        String line = "server message $E$ test delimiter handler !! $E$ second  message $E$";
        ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server exceptionCaught method run ...");
        ctx.close();
    }
}
