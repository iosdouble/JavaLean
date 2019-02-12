package com.example.network.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName Client
 * @Author nihui
 * @Date 2019/2/12 13:58
 * @Version 1.0
 * @Description TODO
 */
public class Client {
    public static void main(String[] args) {
        String host = null;
        int port = 0;
        if (args.length>0){
            host = args[0];
            port = Integer.parseInt(args[1]);
        }else {
            host = "127.0.0.1";
            port = 8081;
        }
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new Socket(host,port);
            String message = null;

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            writer = new PrintWriter(socket.getOutputStream(),true);
            while (true){
                message = scanner.nextLine();
                if (message.equals("exit")){
                    break;
                }
                writer.println(message);
                writer.flush();
                System.out.println(reader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socket==null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket = null;
            if (reader==null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            reader = null;
            if (writer==null){
                writer.close();
            }
            writer =  null;
        }
    }
}
