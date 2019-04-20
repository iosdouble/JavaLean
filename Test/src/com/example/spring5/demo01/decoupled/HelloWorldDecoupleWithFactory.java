package com.example.spring5.demo01.decoupled;

/**
 * @ClassName HelloWorldDecoupleWithFactory
 * @Author nihui
 * @Date 2019/3/15 14:47
 * @Version 1.0
 * @Description TODO
 */
public class HelloWorldDecoupleWithFactory {
    public static void main(String[] args) {
        MessageRenderer mr = MessageSupportFactory.getInstance().getMessageRenderer();
        MessageProvider pr = MessageSupportFactory.getInstance().getMessageProvider();

        mr.setMessageProvider(pr);
        mr.render();
    }
}
