package com.example.io.datainputstream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName TestDataInputStream
 * @Author nihui
 * @Date 2019/2/26 11:09
 * @Version 1.0
 * @Description TODO
 */
public class TestDataInputStream {
    public static void main(String[] args) throws IOException {
        byte[] bytes = {65,66,67,68,69,70,71,73};
        InputStream in = new ByteArrayInputStream(bytes);
        DataInputStream din = new DataInputStream(in);
        while (din.read()!=-1){
            byte c = din.readByte();
            System.out.println(c);
        }
    }
}
