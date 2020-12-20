package com.jayden.io.bio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerTaskThread extends Thread {
    private Socket socket;

    public ServerTaskThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "正在提供服务");
        // 输入流用来获取到客户端的请求输入
        InputStream inputStream = null;

        //输出流用来给客户端进行反馈数据吃的
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter out = new PrintWriter(outputStream, true);
            // 服务端不断的处理客户端来的请求
            while (true) {
                System.out.println("服务端正在等待客户端的请求输入");
                // 阻塞
                String readLine = in.readLine();
                if (null == readLine) {
                    break;
                }
                System.out.println("接收到的客户端数据为：" + readLine);
                out.println("服务端接收到了，数据为：" + readLine);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}