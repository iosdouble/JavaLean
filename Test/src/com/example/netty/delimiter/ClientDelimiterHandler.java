package com.example.netty.delimiter;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * @ClassName ClientDelimiterHandler
 * @Author nihui
 * @Date 2019/2/20 13:18
 * @Version 1.0
 * @Description TODO
 */
public class ClientDelimiterHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            String message = msg.toString();
            System.out.println("from server : "+message);

        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exceptionCaught method run...");
        ctx.close();
    }
}
