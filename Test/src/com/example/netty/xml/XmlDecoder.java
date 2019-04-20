package com.example.netty.xml;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @ClassName CustomDecoder
 * @Author nihui
 * @Date 2019/4/19 9:00
 * @Version 1.0
 * @Description 对于XML报文的转换
 */

public class XmlDecoder extends LengthFieldBasedFrameDecoder {

    //判断传送客户端传送过来的数据是否按照协议传输，头部信息的大小应该是 byte+byte+int = 1+1+4 = 6
    private static final int HEADER_SIZE = 4;
    private int length;
    private String body;

    /**
     * @param maxFrameLength      解码时，处理每个帧数据的最大长度
     * @param lengthFieldOffset   该帧数据中，存放该帧数据的长度的数据的起始位置
     * @param lengthFieldLength   记录该帧数据长度的字段本身的长度
     * @param lengthAdjustment    修改帧数据长度字段中定义的值，可以为负数
     * @param initialBytesToStrip 解析的时候需要跳过的字节数
     * @param failFast            为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异常
     */
    public XmlDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                      int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength,
                lengthAdjustment, initialBytesToStrip, failFast);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in == null) {
            return null;
        }
        if (in.readableBytes() < HEADER_SIZE) {
            throw new Exception("可读信息段比头部信息都小" + in.readableBytes());
        }
//        byte[] bytes = new byte[8];
//        in.readBytes(bytes);
//        length = Integer.parseInt(new String(bytes));
//        System.out.println("收到的报文长度："+length);
        byte b = in.readByte();
        length = (b & 0xFF);


        if (in.readableBytes() < length) {
            //这里报错说明客户端发送数据的时候没有全部发送
            throw new Exception("body字段长度是" + length + "，实际情况是：" + in.readableBytes());
        }
        ByteBuf buf = in.readBytes(in.readableBytes());
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        body = new String(req, "UTF-8");
        System.out.println(body);
        XmlMsg customMsg = new XmlMsg(length, body);
        return customMsg;
    }

}