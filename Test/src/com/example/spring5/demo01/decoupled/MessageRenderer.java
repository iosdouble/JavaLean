package com.example.spring5.demo01.decoupled;

/**
 * @ClassName MessageRenderer
 * @Author nihui
 * @Date 2019/3/15 14:12
 * @Version 1.0
 * @Description TODO
 */
public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}
