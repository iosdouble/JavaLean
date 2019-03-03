package com.example.io.outputstream;

import java.io.*;

/**
 * @ClassName TestOutputStream
 * @Author nihui
 * @Date 2019/2/26 9:31
 * @Version 1.0
 * @Description TODO
 */
public class TestOutputStream {
    public static void main(String[] args) throws IOException {
        String msg = "Hello World!";
        OutputStream out = new ByteArrayOutputStream();
        out.write(msg.getBytes());
        out.flush();
        System.out.println(out);
        out.close();
    }
}
