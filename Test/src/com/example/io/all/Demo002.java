package com.example.io.all;


import java.io.*;
import java.util.Scanner;

/**
 * @ClassName Demo002
 * @Author nihui
 * @Date 2019/2/26 16:28
 * @Version 1.0
 * @Description TODO
 */
public class Demo002 {
    private static String path = "src/test.txt";
    public static void main(String[] args) throws Exception {
        File file = new File(path);
        Reader reader = new FileReader(file);
        char[] buffer = new char[1024];
        while (reader.read(buffer)!=-1){
            System.out.println(new String(buffer));
        }
        Scanner scanner = new Scanner(System.in);
        Writer writer = new FileWriter(path,true);
        writer.write(scanner.nextLine());
        writer.close();
        reader.close();
    }
}
