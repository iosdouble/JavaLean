package com.example.classloader;

/**
 * @ClassName ClassLoader
 * @Author nihui
 * @Date 2019/2/13 14:57
 * @Version 1.0
 * @Description TODO
 */
public class ClassLoader {
    public static void main(String[] args) {
        System.out.println("爸爸的岁数:" + Son.factor);  //入口
    }
}
class Grandpa {
    static {
        System.out.println("爷爷在静态代码块");
    }
}

class Father extends Grandpa {
    static {
        System.out.println("爸爸在静态代码块");
    }

    public static int factor = 25;

    public Father() {
        System.out.println("我是爸爸~");
    }
}
class Son extends Father {
    static {
        System.out.println("儿子在静态代码块");
    }

    public Son() {
        System.out.println("我是儿子~");
    }
}
