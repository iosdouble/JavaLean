package com.example.network.aio;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName AIOServerHandler
 * @Author nihui
 * @Date 2019/2/12 17:26
 * @Version 1.0
 * @Description TODO
 */
public class AIOServerHandler implements CompletionHandler<AsynchronousSocketChannel,AIOServer> {

    /**
     * 业务处理逻辑
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

    private void doWrite(AsynchronousSocketChannel result){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            System.out.println("enter message send to client > ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            buffer.put(line.getBytes("UTF-8"));
            buffer.flip();
            result.write(buffer);
            //result.write(buffer).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doRead(final AsynchronousSocketChannel channel){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {

                try{
                    System.out.println(attachment.capacity());
                    attachment.flip();
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
