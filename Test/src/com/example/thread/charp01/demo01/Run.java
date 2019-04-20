package com.example.thread.charp01.demo01;

/**
 * @ClassName Run
 * @Author nihui
 * @Date 2019/3/18 8:39
 * @Version 1.0
 * @Description TODO
 */
public class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("运行结束");
    }
}
