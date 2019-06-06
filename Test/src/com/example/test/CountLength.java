package com.example.test;

/**
 * @ClassName CountLength
 * @Author nihui
 * @Date 2019/5/17 14:40
 * @Version 1.0
 * @Description TODO
 */
public class CountLength {
    public static void main(String[] args) {
        String str = "Hello World!";
        String newstr = getLength(str.length(),8)+str;
        System.out.println(newstr);
    }
    private static String getLength(int length, int headLength) {
        String lenStr = "" +length;
        while (lenStr.length()<headLength){
            lenStr = "0"+lenStr;
        }
        return lenStr;
    }
}
