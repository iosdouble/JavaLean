package com.example.netty.nettyoom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName RouterServerHandler
 * @Author nihui
 * @Date 2019/5/10 11:14
 * @Version 1.0
 * @Description TODO
 */
public class RouterServerHandler extends ChannelHandlerAdapter {

    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    PooledByteBufAllocator allocator = new PooledByteBufAllocator(false);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf reqMeg = (ByteBuf) msg;
        byte[] body = new byte[reqMeg.readableBytes()];
        executorService.execute(()->{
            //解析请求消息，做路由转发

            ByteBuf respMeg = allocator.heapBuffer(body.length);
            reqMeg.writeBytes(body);
            ctx.writeAndFlush(respMeg);
        });
    }

    //后续其他逻辑
}
