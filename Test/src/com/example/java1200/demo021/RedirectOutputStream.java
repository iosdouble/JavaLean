package com.example.java1200.demo021;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @ClassName RedirectOutputStream
 * @Author nihui
 * @Date 2019/3/14 17:36
 * @Version 1.0
 * @Description TODO
 */
public class RedirectOutputStream {

    public static void main(String[] args) {
        try{
            //保存原输出流
            PrintStream out = System.out;
            //创建文件输出流
            PrintStream ps = new PrintStream("./log.txt");
            //设置使用新的输出流
            System.setOut(ps);
            //定义内容
            int age = 18;
            System.out.println("年龄变量成功定义，初始值为18");
            String sex="女";
            System.out.println("性别变量成功定义，初始值为女");
            String info = "这个是一个"+sex+"孩子"+",应该有"+age+"岁了！";
            System.out.println("整合两个变量为info字符变量，其结果是："+info);
            //恢复原有输出流
            System.setOut(out);
            System.out.println("程序运行完毕，请查看日志文件。");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
