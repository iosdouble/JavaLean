package com.example.spring5.demo01.decoupled;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * @ClassName MessageSupportFactory
 * @Author nihui
 * @Date 2019/3/15 14:37
 * @Version 1.0
 * @Description TODO
 */
public class MessageSupportFactory {
    private static MessageSupportFactory instance;

    private Properties props;
    private MessageRenderer renderer;
    private MessageProvider provider;

    private MessageSupportFactory(){
        props = new Properties();

        try{
            //查找具有给定名称的资源
            //props.load(this.getClass().getResourceAsStream("msf.properties"));
            props.load(new FileReader(new File("src/msf.properties")));
            String renderClass = props.getProperty("readerer.class");
            String providerClass = props.getProperty("provider.class");

            renderer = (MessageRenderer) Class.forName(renderClass).newInstance();
            provider = (MessageProvider) Class.forName(providerClass).newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static {
        instance = new MessageSupportFactory();
    }
    public static MessageSupportFactory getInstance(){
        return instance;
    }
    public MessageRenderer getMessageRenderer() {
        return renderer;
    }

    public MessageProvider getMessageProvider() {
        return provider;
    }
}
