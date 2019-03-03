package com.example.io.all;

import java.io.*;

/**
 * @ClassName ByteArrayStreamTest
 * @Author nihui
 * @Date 2019/2/26 10:40
 * @Version 1.0
 * @Description TODO
 */
public class ByteArrayStreamTest {
    public static void main(String[] args) throws IOException {
        OutputStream out = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];

        byte[] bytes = {65,66,67,68,69,70,71,72};

        InputStream in = new ByteArrayInputStream(bytes);

        in.read(buffer);
        out.write(buffer);
        System.out.println(new String(buffer));
        System.out.println(out);
        in.close();
        out.close();
    }
}
