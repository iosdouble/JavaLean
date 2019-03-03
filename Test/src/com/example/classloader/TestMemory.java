package com.example.classloader;

import org.apache.commons.logging.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TestMemory
 * @Author nihui
 * @Date 2019/2/25 17:11
 * @Version 1.0
 * @Description TODO
 */
public class TestMemory {
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY");
        String today = format.format(new Date());
        System.out.println(today);
    }
}
