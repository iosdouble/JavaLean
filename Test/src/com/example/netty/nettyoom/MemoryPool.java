package com.example.netty.nettyoom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * @ClassName MemoryPool
 * @Author nihui
 * @Date 2019/5/10 14:19
 * @Version 1.0
 * @Description TODO
 */
public class MemoryPool {

    static void unPoolTest(){
        long beginTime = System.currentTimeMillis();
        ByteBuf buf = null;
        int maxTime = 100000000;
        for (int i = 0; i < maxTime; i++) {
            buf = Unpooled.buffer(10*1024);
            buf.release();
        }

        System.out.println("Execute "+maxTime+" times cost time : "+(System.currentTimeMillis()-beginTime));
    }

    static void poolTest(){
        PooledByteBufAllocator allocator = new PooledByteBufAllocator(false);

        long beginTime = System.currentTimeMillis();

        ByteBuf buf = null;

        int maxTimes = 100000000;

        for (int i = 0; i < maxTimes; i++) {
            buf = allocator.heapBuffer(10*1024);
            buf.release();
        }
        System.out.println("Execute "+maxTimes+" time cost time : "+(System.currentTimeMillis()-beginTime));
    }
}
