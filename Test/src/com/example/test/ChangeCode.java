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
        String sx = Integer.toHexString(1307);
        sx = "0x" + sx;

        System.out.println(sx);



        //10011010010
        //   11100001
        //10011001100

        System.out.println((char)60);

        byte b = (byte) 0xAA;
        System.out.println(b);

        System.out.println();

        System.out.println(toHex(1307));

        byte b1 = (byte)((64 >> 6) & 0xFF);
        System.out.println(b1);

    }

    public static String toHex(int num){
        if (num==0){
            return "0";
        }
        String result = "";
        while (num!=0){
            int x = num&0xff;
            result = x+result;
            num=(num>>>4);
        }
        return result;
    }
}
