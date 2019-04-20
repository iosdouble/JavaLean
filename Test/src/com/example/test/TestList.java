package com.example.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestList
 * @Author nihui
 * @Date 2019/4/15 14:53
 * @Version 1.0
 * @Description TODO
 */
public class TestList {
    public static void printTo(){
        List petsList = new ArrayList();
        petsList.add("cat");
        petsList.add("dog");
        forname(petsList);
        System.out.println(petsList);
    }

    private static void forname(List petsList) {
        List newList = new ArrayList();
        newList.add("neww");
        newList.add("safdsaf");
        petsList = newList;
    }

    public static void main(String[] args) {
        printTo();
    }
}
