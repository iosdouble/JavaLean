package com.example.netty.second;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * @ClassName ClientFixedLenghtHandler
 * @Author nihui
 * @Date 2019/2/19 13:24
 * @Version 1.0
 * @Description TODO
 */
public class ClientFixedLenghtHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            String message = msg.toString();
            System.out.println("from server :"+message);
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("client exeptionCaught method run...");
        ctx.close();
    }
}
