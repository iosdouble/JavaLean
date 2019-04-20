package com.java8.demo01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName Test
 * @Author nihui
 * @Date 2019/3/19 13:46
 * @Version 1.0
 * @Description TODO
 */
public class Test {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),new Apple(150,"green"),new Apple(120,"red"));

        List<Apple> greenApples = filterApples(inventory,Test::isGreenApple);
        System.out.println(greenApples);

        List<Apple> heavyApples = filterApples(inventory,Test::isHeavyApple);
        System.out.println(heavyApples);

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples2);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);

    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ("green".equals(apple.getColor())) {
                result.add(apple);  //将符合要求的苹果加入到一个List集合中
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (apple.getWeight() > 150) {
                result.add(apple);  //将超过重量的苹果加入到集合中
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();  //创建一个装载集合
        for(Apple apple : inventory){
            if(p.test(apple)){  //判断是否满足条件，这个条件具体是由Predicate参数传入进去。
                result.add(apple);
            }
        }
        return result;
    }
}
