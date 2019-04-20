package com.example.test;

import java.util.Date;
import java.util.TreeMap;

/**
 * @ClassName TestMap
 * @Author nihui
 * @Date 2019/4/15 15:13
 * @Version 1.0
 * @Description TODO
 */
public class TestMap {


    public static void main(String[] args) {
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        String demo = new String("nihui");
        treeMap.put(demo,1);
        treeMap.put(new String("test"),2);

        System.out.println(treeMap.get(demo));
        System.out.println(new String("test"));
    }
}
