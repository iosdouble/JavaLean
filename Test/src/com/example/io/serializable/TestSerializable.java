package com.example.io.serializable;

import java.io.*;

/**
 * @ClassName TestSerializable
 * @Author nihui
 * @Date 2019/2/26 16:45
 * @Version 1.0
 * @Description 测试序列化
 */
public class TestSerializable {
    private static String path = "src/test.txt";
    public static void main(String[] args) throws Exception {
        User user = new User("nihui",24);
        System.out.println(user.toString());

        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(path));
        oout.writeObject(user);
        oout.close();

        File file = new File(path);
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        User u = (User) oin.readObject();
        System.out.println(u.toString());

    }
}
