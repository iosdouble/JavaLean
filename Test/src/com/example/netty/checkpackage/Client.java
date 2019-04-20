package com.example.netty.checkpackage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws  IOException {




        final int port = 10001;
        final String clientname = "clientThread";
        Socket socket = new Socket("127.0.0.1", port);
        final OutputStream out = socket.getOutputStream();
        new Thread(clientname + "_write") {
            @Override
            public void run() {
                int a = 0;
                for(int i=0;i<100;i++) {
                     CustomMsg customMsg = new CustomMsg((byte)0xAB, (byte)0xCD, "Hello,Netty".length(), "Hello,Netty");
                    try {
                        out.write(customMsg.getBytes());
                        out.flush();
                        a++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        //socket.close();
    }
}