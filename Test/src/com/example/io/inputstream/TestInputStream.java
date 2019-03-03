package com.example.io.inputstream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName TestInputStream
 * @Author nihui
 * @Date 2019/2/26 8:41
 * @Version 1.0
 * @Description TODO
 */
public class TestInputStream {
    public static void main(String[] args) throws IOException {
        byte[] bytes = {97,65,66,67,68,69,50};
        //byte[] bytes = null;
        InputStream in = new ByteArrayInputStream(bytes);
        byte[] buffer = new byte[1024];
        //in.skip(2);
        int len = in.available();
        System.out.println("不受阻塞获取读取数据长度+"+len);
        while (in.read(buffer)!=-1){
            String msg = new String(buffer);
            System.out.println(msg);
        }
        in.close();
    }
}
