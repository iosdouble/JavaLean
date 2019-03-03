package com.example.classloader;

/**
 * @ClassName LoaderTest
 * @Author nihui
 * @Date 2019/2/22 10:58
 * @Version 1.0
 * @Description TODO
 */
public class LoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = HelloWorld.class.getClassLoader();
        System.out.println(loader);
        //使用ClassLoader.loadClass来加载，不会执行初始化模块
        //loader.loadClass("com.example.classloader.Test");
        //使用Class.forName()来加载默认执行初始化块
        //Class.forName("com.example.classloader.Test");
        //使用Class.forNmae()来加载类，并指定ClassLoader，初始化时不执行静态代码块
        Class.forName("com.example.classloader.Test",false,loader);

    }
}
