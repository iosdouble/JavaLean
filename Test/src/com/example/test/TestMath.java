package com.example.test;

/**
 * @ClassName TestMath
 * @Author nihui
 * @Date 2019/4/24 9:01
 * @Version 1.0
 * @Description TODO
 */
public class TestMath {
    public static void main(String[] args) {
        int cap = 99;
        int n = cap-1;
        n |= n>>>1;
        n |= n>>>2;
        n |= n>>>4;
        n |= n>>>8;
        n |= n>>>16;
        System.out.println(n);
        int number = 1;

        /**
         * | 表示或
         *
         *  5
         *  101
         *
         *  48
         *  1001000
         *
         *  1001101
         */
        number |= 3;
        System.out.println(number);

        /**
         * Java >>>无符号右移
         */
        int b = 7;
        /**
         * 111
         *
         * 100
         */
        b = b>>>1;
        System.out.println(b);
    }
}
