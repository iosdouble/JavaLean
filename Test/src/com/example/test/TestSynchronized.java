package com.example.test;

import java.util.Random;

/**
 * @ClassName TestSynchronized
 * @Author nihui
 * @Date 2019/4/15 14:57
 * @Version 1.0
 * @Description TODO
 */
public class TestSynchronized {
    public synchronized static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                hello();
            }
        });
        t.start();

        try {
            Thread.sleep(new Random().nextInt(500));
            System.out.println("There");
        } catch (Exception e) {

        }

    }

    public static synchronized void hello() {
        try {
            Thread.sleep(new Random().nextInt(200));
            System.out.println("hello");
        } catch (Exception e) {

        }
    }
}
