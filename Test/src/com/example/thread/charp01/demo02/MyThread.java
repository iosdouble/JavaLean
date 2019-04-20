package com.example.thread.charp01.demo02;

/**
 * @ClassName MyThread
 * @Author nihui
 * @Date 2019/3/18 8:41
 * @Version 1.0
 * @Description TODO
 */
public class MyThread extends Thread {
    private int i;

    public MyThread(int i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i);
    }
}
