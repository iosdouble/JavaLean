package com.example.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestParameterValue
 * @Author nihui
 * @Date 2019/5/7 11:28
 * @Version 1.0
 * @Description TODO
 */
public class TestParameterValue {
    public static void main(String[] args) {
        Map<String,String> stringMap = new HashMap<>();

        stringMap.put("2","world");

        change(stringMap,"Hello");
        System.out.println(stringMap.toString());
    }

    private static void change(Map<String,String> stringMap, String hello) {
        stringMap.put("1",hello);
    }
}
