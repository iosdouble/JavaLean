package com.example.classloader;

/**
 * @ClassName GetClassLoader
 * @Author nihui
 * @Date 2019/2/22 10:12
 * @Version 1.0
 * @Description 获取到类加载器
 */
public class GetClassLoader {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }
}
