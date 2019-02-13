package com.example.network.aio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;

/**
 * @ClassName AIOServerHandler
 * @Author nihui
 * @Date 2019/2/12 17:26
 * @Version 1.0
 * @Description TODO
 */
public class AIOServerHandler implements CompletionHandler<AsynchronousSocketChannel,AIOServer> {

    /**
     * 业务处理逻辑 当请求到来后，监听成功，应该做什么。
     * 一定要实现的逻辑是为下一次客户端请求做准备
     * @param result 就是和客户端直接建立关联的通道
     *               无论是在BIO/NIO/AIO 中一旦连接建立，两端是平等的。
     *               result中有通道中所有有关数据。如OS操作系统准备好的读取数据缓存，或者等待返回数据的缓存
     *
     * @param attachment
     */
    @Override
    public void completed(AsynchronousSocketChannel result, AIOServer attachment) {
        attachment.getServerSocketChannel().accept(attachment,this);
        doRead(result);
    }

    /**
     * 当服务端代码出现异常的时候做的事情
     * @param exc
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AIOServer attachment) {
        exc.printStackTrace();
    }

    /**
     * 真实项目中，服务器返回的结果应该是根据客户端的请求数据计算得到的，不是等待控制台输入的
     *
     * @param result
     */
    private void doWrite(AsynchronousSocketChannel result){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            System.out.println("enter message send to client > ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            buffer.put(line.getBytes("UTF-8"));
            //一定要服务
            buffer.flip();
            //write方法是一个异步操作具体实现是由OS实现，可以增加get方法，实现阻塞，等待OS的写操作
            result.write(buffer);
            //result.write(buffer).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doRead(final AsynchronousSocketChannel channel){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        /**
         * 异步读操作
         *
         * destination 目的地，处理客户端传递的中转缓存，可以不使用
         * attachment 处理客户端传递数据的对象，通常使用buffer处理
         * Handler 处理逻辑
         */
        channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            /**
             * 业务逻辑，读取客户端传输数据
             * @param result
             * @param attachment 在completed方法执行的时候，OS已经将客户端请求的数据写入到了Buffer中
             *                   但是没有复位，使用前一定要复位。
             */
            @Override
            public void completed(Integer result, ByteBuffer attachment) {

                try{
                    System.out.println(attachment.capacity());
                    //复位操作
                    attachment.flip();
                    //打印操作内容
                    System.out.println("from client : " + new String(attachment.array(), "UTF-8"));
                    doWrite(channel);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }
}
