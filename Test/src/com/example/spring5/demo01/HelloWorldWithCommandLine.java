package com.example.spring5.demo01;

/**
 * @ClassName HelloWorldWithCommandLine
 * @Author nihui
 * @Date 2019/3/15 14:10
 * @Version 1.0
 * @Description TODO
 */
public class HelloWorldWithCommandLine {
    public static void main(String[] args) {
        if (args.length>0){
            System.out.println(args[0]);
        }else {
            System.out.println("Hello World!");
        }
    }
}
