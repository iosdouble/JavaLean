package com.example.spring5.demo01.decoupled;

/**
 * @ClassName HelloWorldMessageProvider
 * @Author nihui
 * @Date 2019/3/15 14:14
 * @Version 1.0
 * @Description TODO
 */
public class HelloWorldMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
