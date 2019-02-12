package com.example.network.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @ClassName NIOServer
 * @Author nihui
 * @Date 2019/2/12 15:30
 * @Version 1.0
 * @Description TODO
 */
public class NIOServer implements Runnable{

    //多路复用器，选择器。用于注册通道用的
    private Selector selector;
    //定义了两个缓存。分别用于读和写。初始化空间大小单位为字节
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) {
        new Thread(new NIOServer(8081)).start();
    }

    public NIOServer(int port){
        init(port);
    }

    private void init(int port) {
        try {
            System.out.println("server starting at port "+ port+"......");
            //开启多路复用器
            this.selector = Selector.open();
            //开启服务通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //非阻塞，如果传递参数为true，为阻塞模式
            serverSocketChannel.configureBlocking(false);
            //绑定端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            //注册，并标记当前服务通道状态
            serverSocketChannel.register(this.selector,SelectionKey.OP_ACCEPT);
            System.out.println("server started.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true){
            try{
                //阻塞方法，当至少一个通道被选中，此方法返回。
                //通道是否选中，由注册到多路服务器中的通道标记决定
                this.selector.select();
                //返回选中的通道标记集合，集合中保存的是通道标记。相当于通道ID
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();

                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    //将本次要处理的通道从集合中删除，下次循环根据新的通道列表再次执行必要的业务逻辑
                    keys.remove();
                    //通道是否有效
                    if (key.isValid()){
                        //阻塞状态
                        try{
                            if (key.isAcceptable()){
                                accept(key);
                            }
                        }catch (CancelledKeyException cke){
                            key.cancel();
                        }
                        //可读状态
                        try {
                            if (key.isReadable()){
                                read(key);
                            }
                        }catch (CancelledKeyException cke){
                            key.cancel();
                        }
                        //可写状态
                        try{
                            if (key.isWritable()){
                                write(key);
                            }
                        }catch (CancelledKeyException cke){
                            key.cancel();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void write(SelectionKey key){
        this.writeBuffer.clear();
        SocketChannel channel = (SocketChannel) key.channel();
        Scanner reader = new Scanner(System.in);
        try{
            System.out.println("put message for send to client > ");
            String line = reader.nextLine();
            writeBuffer.put(line.getBytes("UTF-8"));
            writeBuffer.flip();
            channel.write(writeBuffer);
            channel.register(this.selector,SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read(SelectionKey key){
        try{
            //清空读缓存
            this.readBuffer.clear();
            //获取通道
            SocketChannel channel = (SocketChannel) key.channel();
            //将通道中的数据读取到缓存中，这个通道中的数据就是客户端发送到服务器的
            int readLength = channel.read(readBuffer);
            //检查客户端是否写入数据
            if (readLength==-1){
                //关闭通道
                key.channel().close();
                //关闭连接
                key.cancel();
                return;
            }
            //flip，NIO中最复杂的操作就是Buffer的控制。这个是一个游标复位的操作
            this.readBuffer.flip();
            //字节数组保存具体数据。这个方法是获取Buffer中有效数据长度的方法
            byte[] datas = new byte[readBuffer.remaining()];
            //是将Buffer有效数据保存到字节数组中
            readBuffer.get(datas);
            System.out.println("from "+channel.getRemoteAddress()+"client : "+ new String(datas,"UTF-8"));
            //注册通道，标记为写操作
            channel.register(this.selector,SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                key.channel().close();
                key.cancel();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    private void accept(SelectionKey key){
        try{
            //此通道为Init方法中注册到Selector上的ServerSocketChannel
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            //阻塞方法，当客户端发起请求后返回。此通管道与客户端一一对应。
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            //设置对应客户端的通道标记状态，此通道为读取数据使用的。
            channel.register(this.selector,SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
