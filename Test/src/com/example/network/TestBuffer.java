package com.example.network;

import java.nio.ByteBuffer;

/**
 * @ClassName TestBuffer
 * @Author nihui
 * @Date 2019/2/12 16:40
 * @Version 1.0
 * @Description
 *
 * Buffer 的应用固定逻辑
 *
 * 写操作顺序
 * 1. clear()
 * 2. put()/get() 读写操作
 * 3. flip() 重置游标
 * 4 SocketChannel write(buffer) 将缓存数据发送到网络的另外一段
 * 5.clear()
 *
 * 读操作顺序
 * 1.clear()
 * 2.SocketChannel read(buffer) 从网络中读取数据
 * 3.buffer.flip 重置游标
 * 4.get() 读取数据
 * 5. clear()
 */
public class TestBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(8);

        byte[] temp = new byte[]{3,2,1};
        //写入数据之前：java.nio.HeapByteBuffer[pos=0 lim=8 cap=8]
        // pos - 游标位置 ，lim - 限制数量， cap - 最大容量
        System.out.println("写入数据之前："+buffer);
        //写入字节数组到缓存
        buffer.put(temp);

        //写入数据之后：java.nio.HeapByteBuffer[pos=3 lim=8 cap=8]
        System.out.println("写入数据之后："+buffer);

        //重置游标
        buffer.flip();

        //重置游标之后：java.nio.HeapByteBuffer[pos=0 lim=3 cap=8]
        System.out.println("重置游标之后："+buffer);

        //这里 ramaining获取到的值是使用了 lim - pos
        for (int i = 0; i <buffer.remaining() ; i++) {
            int data = buffer.get(i);
            System.out.println(i+"  -  " +data);
        }
    }
}
