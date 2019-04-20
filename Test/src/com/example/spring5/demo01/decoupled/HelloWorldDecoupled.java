package com.example.spring5.demo01.decoupled;

/**
 * @ClassName HelloWorldDecoupled
 * @Author nihui
 * @Date 2019/3/15 14:18
 * @Version 1.0
 * @Description TODO
 */
public class HelloWorldDecoupled {
    public static void main(String[] args) {
        MessageRenderer mr = new StandardOutMessageRenderer();
        MessageProvider mp = new HelloWorldMessageProvider();

        mr.setMessageProvider(mp);
        mr.render();
    }
}
