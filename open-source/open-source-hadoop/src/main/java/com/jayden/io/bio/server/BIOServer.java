package com.jayden.io.bio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangwei
 * @description 同步阻塞，一对一的服务器程序
 * BIO两大缺点：
 * 1、客户端和服务端是1：1的情况，服务器的资源不够用
 * 2、有两处阻塞：socket = serverSocket.accept、in.readLine
 * <p>
 * 解决方案：
 * 既然一个服务线程在请求完了连接请求之后会一直阻塞在in.readLine上等待用户发送请求，
 * 当前等到用户发送请求过来的这段时间内，这个线程处于阻塞，那么就可以在完成连接的时候把这个线程调度给其他的应用程序使用，基于通过NIO来实现
 */
public class BIOServer {
    private static final int PORT = 9090;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", PORT));
            while (true) {
                // 阻塞,等待客户端发送链接请求过来
                socket = serverSocket.accept();
                // 服务端接受一个请求用一个线程来处理，缺点是如果是资源不够的情况无法处理海量的请求
                new Thread(new ServerTaskThread(socket)).start();
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
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
