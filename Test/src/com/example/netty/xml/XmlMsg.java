package com.example.netty.xml;

import java.io.UnsupportedEncodingException;

public class XmlMsg {
    // 主题信息的长度
    private int length;

    // 主题信息
    private String body;

    public XmlMsg() {

    }

    public XmlMsg(int length, String body) {
        this.length = length;
        this.body = body;
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
//    public byte[] getBytes() throws UnsupportedEncodingException {
//        String xml =getNoString(body);
//        byte[] bytes  = xml.getBytes();
//        System.out.println(new String(bytes));
//        for(byte b:bytes){
//            System.out.println(b);
//        }
//        System.out.println("实际发送的长度"+bytes.length);
//        return bytes;
//    }

    public byte[] getBytes() throws UnsupportedEncodingException {
        byte[] bodyBytes = this.body.getBytes();
        int bodyLength = bodyBytes.length;
        byte lenzip = (byte) (bodyLength & 0xFF);
        byte[] bytes = new byte[bodyLength+1];
        bytes[0] = lenzip;
        System.out.println(new String(bytes));
        for (int i = 0; i < bodyBytes.length; i++) {
            bytes[i + 1] = bodyBytes[i];
        }
        for(byte b:bytes){
            System.out.println(b);
        }
        System.out.println("实际发送的长度"+bytes.length);
        return bytes;
    }

    public byte[] getBytest(){
        //将请求信息转换为字节数组
        byte[] bodyBytes = this.body.getBytes();
        //报文体数据字节数组长度
        int bodyLength = bodyBytes.length;
        //实际操作的数据在原来数据的基础上加上六位长度头
        byte[] bytes = new byte[bodyLength + 4];

        //表示长度除以2的24次方
        //0xFF 表示255 byte 11111111
        bytes[0] = (byte)((bodyLength >> 24) & 0xFF);
        //表示长度除以2的16次方
        bytes[1] = (byte)((bodyLength >> 16) & 0xFF);
        bytes[2] = (byte)((bodyLength >> 8) & 0xFF);
        bytes[3] = (byte)(bodyLength & 0xFF);
        for (int i = 0; i < bodyBytes.length; i++) {
            bytes[i + 4] = bodyBytes[i];
        }

        for (byte b : bytes) {
            System.out.println(b);
        }

        System.out.println(new String(bytes));
        return bytes;
    }

    private static String getNoString(String src) {
        String temp = getLength(src.length(), 8) + src;
        System.out.println(src.length());
        return temp;
    }

    //计算报文长度
    private static String getLength(int length, int headLength) {
        String lenStr = "" + length;
        while (lenStr.length() < headLength) {
            lenStr = "0" + lenStr;
        }
        return lenStr;
    }
}