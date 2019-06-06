package com.example.test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName TestString
 * @Author nihui
 * @Date 2019/5/7 14:49
 * @Version 1.0
 * @Description TODO
 */
public class TestString {

    private String name;
    private String sex;

    public static void main(String[] args) {
        String string = "setName";

        System.out.println(string.length());

        TestString testString = new TestString();

        for (Method method : testString.getClass().getMethods()) {
            System.out.println(method.toString());
            //判断获取方法名的条件
            if (method.getName().startsWith("set")
                    && method.getParameterTypes().length == 1
                    && Modifier.isPublic(method.getModifiers())) {
                //对于方法参数的处理
                Class<?> pt = method.getParameterTypes()[0];
                System.out.println("pt -------->" + pt.toString());
            }

            String property = method.getName().length() > 3 ? method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4) : "";
            System.out.println("property————————>"+property);
        }

        String s = String.format("\nif (arg%d == null) throw new IllegalArgumentException(\"url == null\");",
                1);
        System.out.println(s);
    }
    public void testMethod (String name){

    }

    public int testMethod1 () {
        return 1;
    }

    public String getName () {
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getSex () {
        return sex;
    }

    public void setSex (String sex){
        this.sex = sex;
    }

}
