package com.example.classloader;

import java.io.*;

/**
 * @ClassName MyClassLoader
 * @Author nihui
 * @Date 2019/2/22 11:26
 * @Version 1.0
 * @Description TODO
 */
public class MyClassLoader extends ClassLoader {
    private String root;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData==null){
            throw new ClassNotFoundException();
        }else {
            return defineClass(name,classData,0,classData.length);
        }
    }

    private byte[] loadClassData(String className) {
        String fileName=root+ File.separatorChar+className.replace('.',File.separatorChar)+".class";
        try{
            InputStream ins = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length=ins.read(buffer))!=-1){
                baos.write(buffer,0,length);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader();
        classLoader.setRoot("F:\\developersrc");
        Class<?> testClass = null;
        try {
            testClass = classLoader.loadClass("Test");
            Object object = testClass.newInstance();
            System.out.println(object.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
