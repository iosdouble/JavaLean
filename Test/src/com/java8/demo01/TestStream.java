package com.java8.demo01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName TestStream
 * @Author nihui
 * @Date 2019/3/19 14:01
 * @Version 1.0
 * @Description TODO
 */
public class TestStream {

    public static void main(String[] args) {
        //顺序处理
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),new Apple(150,"green"),new Apple(120,"red"));
        List<Apple> heavyApples = inventory.stream().filter((Apple a)->a.getWeight()>120).collect(Collectors.toList());
        System.out.println(heavyApples);

        //并行处理
        List<Apple> h = inventory.parallelStream().filter((Apple a)->a.getWeight()>120).collect(Collectors.toList());
        System.out.println(h);
    }
}
