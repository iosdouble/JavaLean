package com.example.thread.charp01.demo01;

/**
 * @ClassName MyThread
 * @Author nihui
 * @Date 2019/3/18 8:38
 * @Version 1.0
 * @Description TODO
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("MyThread");
    }
}
