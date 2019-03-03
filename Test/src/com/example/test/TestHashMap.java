package com.example.test;

import java.util.HashMap;
import java.util.Random;

/**
 * @ClassName TestHashMap
 * @Author nihui
 * @Date 2019/2/27 15:39
 * @Version 1.0
 * @Description TODO
 */
public class TestHashMap {
    public static String creatrandom(int count){
        String source = "0123456789";
        Random rand  = new Random();
        StringBuffer flag  =  new StringBuffer();
        for (int i = 0;i<count;i++){
            flag.append(source.charAt(rand.nextInt(9)));
        }
        return flag.toString();
    }
    public static void main(String[] args) {
        System.out.println(TestHashMap.creatrandom(18));
    }
}
