package com.example.test;

/**
 * @ClassName TestBoolean
 * @Description TODO
 * @Author nihui
 * @Date 2019/6/6 14:13
 * @Version 1.1
 **/
public class TestBoolean {

    public static void main(String[] args) {
        String str = null;
        System.out.println(str==null);
        if (str==null){
            System.out.println("条件为真执行");
        }else {
            System.out.println("条件为假执行");
        }
    }
}
