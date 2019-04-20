package com.example.netty.checkpackage;

public class CustomMsg {

    // 类型 系统编号 0xAB 表示A系统，0xBC 表示B系统
    private byte type;

    // 信息标志 0xAB 表示心跳包 0xBC 表示超时包 0xCD 业务信息包
    private byte flag;

    // 主题信息的长度
    private int length;

    // 主题信息
    private String body;

    public CustomMsg() {

    }

    public CustomMsg(byte type, byte flag, int length, String body) {
        this.type = type;
        this.flag = flag;
        this.length = length;
        this.body = body;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    /**
     * 数据转换
     * @return
     */
    public byte[] getBytes() {
        //将请求信息转换为字节数组
        byte[] bodyBytes = this.body.getBytes();
        //报文体数据字节数组长度
        int bodyLength = bodyBytes.length;
        //实际操作的数据在原来数据的基础上加上六位长度头
        byte[] bytes = new byte[bodyLength + 6];
        //拼接报文头
        bytes[0] = this.type;
        bytes[1] = this.flag;

        //表示长度除以2的24次方
        //0xFF 表示255 byte 11111111
        bytes[2] = (byte)((bodyLength >> 24) & 0xFF);
        //表示长度除以2的16次方
        bytes[3] = (byte)((bodyLength >> 16) & 0xFF);
        bytes[4] = (byte)((bodyLength >> 8) & 0xFF);
        bytes[5] = (byte)(bodyLength & 0xFF);
        for (int i = 0; i < bodyBytes.length; i++) {
            bytes[i + 6] = bodyBytes[i];
        }
        System.out.println(new String(bytes));

        for (byte b : bytes) {
            System.out.println(b);
        }
        return bytes;
    }
}