package com.example.io.all;

import java.io.*;
import java.util.Scanner;

/**
 * @ClassName Demo0003
 * @Author nihui
 * @Date 2019/2/26 16:36
 * @Version 1.0
 * @Description TODO
 */
public class Demo003   {
    private static String path = "src/test.txt";
    public static void main(String[] args) throws Exception {
        File file = new File(path);
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(path));
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len=bin.read(buffer))!=-1){
            System.out.println(new String(buffer));
        }
        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path,true));
        Scanner scanner = new Scanner(System.in);
        bout.write(scanner.nextLine().getBytes("UTF-8"));
        bout.close();
        bin.close();
    }
}
