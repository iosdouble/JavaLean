package com.example.io.all;

import java.io.*;
import java.util.Scanner;

/**
 * @ClassName Demo001
 * @Author nihui
 * @Date 2019/2/26 16:17
 * @Version 1.0
 * @Description TODO
 */
public class Demo001 {
    private static String path = "src/test.txt";
    public static void main(String[] args) throws Exception {
        File file  = new File(path);
        InputStream fin = new FileInputStream(file);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len=fin.read(buffer))!=-1){
            System.out.println(new String(buffer));
        }
        Scanner scanner = new Scanner(System.in);

        OutputStream fout = new FileOutputStream(path,true);
        byte[] bytes = scanner.nextLine().getBytes("UTF-8");
        fout.write(bytes);
        fout.flush();


        fout.close();
        fin.close();
    }
}
