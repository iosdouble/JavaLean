package com.example.network.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Server
 * @Author nihui
 * @Date 2019/2/12 11:13
 * @Version 1.0
 * @Description TODO
 */
public class Server {
    public static void main(String[] args) {
        int port = genPort(args);

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new Handler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int genPort(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("输入参数有误");
                return 8081;
            }
        } else {
            return 8081;
        }
    }

    private static class Handler implements Runnable {
        Socket socket = null;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            BufferedReader reader = null;
            PrintWriter writer = null;

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

                String readMessage = null;

                while (true) {
                    System.out.println("server reading ......");
                    if ((readMessage = reader.readLine()) == null) {
                        break;
                    }
                    System.out.println(readMessage);
                    writer.println("server recive:" + readMessage);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                socket = null;
                if (reader == null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                reader = null;
                if (writer == null) {
                    writer.close();
                }
                writer = null;
            }

        }
    }
}
