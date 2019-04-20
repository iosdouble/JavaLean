package com.example.test;

import static java.lang.Byte.parseByte;

/**
 * @ClassName ChangeCode
 * @Author nihui
 * @Date 2019/4/19 9:44
 * @Version 1.0
 * @Description TODO
 */
public class ChangeCode {
    public static void main(String[] args) {
       // System.out.println(Integer.parseInt("0xAB",16));
        System.out.format("%d",0xAB).println();
        System.out.format("%d",0xBC).println();
        System.out.format("%d",0xFF).println();
        System.out.format("%d",0x0C).println();
        System.out.format("%d",0x000C).println();
        System.out.format("%d",0xCAFE).println();
//        char c = 171;
//        System.out.println(c);
//        char e = 188;
//        System.out.println(e);
        System.out.println((byte)0xAB);
        System.out.println((byte)0xCD);
        System.out.println(0xFF);
        System.out.println(120>>2);
        System.out.println(0xFF&12345);
        System.out.println(Integer.toBinaryString(1234));
        System.out.println(Integer.toBinaryString(225));
        System.out.println(Integer.toHexString(1307));


        //10011010010
        //   11100001
        //10011001100
    }
}
