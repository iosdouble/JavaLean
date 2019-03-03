package com.example.test;

/**
 * @ClassName TestStatic
 * @Author nihui
 * @Date 2019/2/27 9:51
 * @Version 1.0
 * @Description TODO
 */
public class TestStatic {
    static int a;
    int b;
    static int c;
    public int aTestStatic(){
        a++;
        return a;
    }
    public int bTestStatic(){
        b++;
        return b;
    }
    public static int cTestStatic(){
        c++;
        return c;
    }
    public static void main(String[] args) {
        TestStatic atestStatic = new TestStatic();
        TestStatic btestStatic = new TestStatic();
        TestStatic ctestStatic = new TestStatic();
        atestStatic.aTestStatic();
        System.out.println(atestStatic.aTestStatic());
        btestStatic.bTestStatic();
        System.out.println(btestStatic.bTestStatic());
        ctestStatic.cTestStatic();
        System.out.println(ctestStatic.cTestStatic());
    }
}
