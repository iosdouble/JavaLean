package com.example.network.base;

import com.example.netty.third.EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName EchoPlay
 * @Author nihui
 * @Date 2019/2/26 8:24
 * @Version 1.0
 * @Description TODO
 */
public class EchoPlay {
    public String echo(String msg){
        return "echo:"+msg;
    }

    public void talk() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = null;
        while ((msg=br.readLine())!=null){
            System.out.println(echo(msg));
            if (msg.equals("bye")){
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoPlay().talk();
    }
}
