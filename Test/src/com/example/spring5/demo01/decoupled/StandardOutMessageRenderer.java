package com.example.spring5.demo01.decoupled;

/**
 * @ClassName StandardOutMessageRenderer
 * @Author nihui
 * @Date 2019/3/15 14:15
 * @Version 1.0
 * @Description TODO
 */
public class StandardOutMessageRenderer implements MessageRenderer {
    private MessageProvider messageProvider;
    @Override
    public void render() {
        if (messageProvider==null){
            throw new RuntimeException("You must set the property messageProvider of class:"+StandardOutMessageRenderer.class.getName());
        }
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;

    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
