package com.jayden.balance.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wangwei
 * @description 完全随机负载均衡算法
 * <p>
 * 实际并不是那么随机，有的服务器经常被获得到，有的服务器获得的次数比较少，
 * 但是当有充足的请求次数，就会越来越平均，这正是随机数的一个特性
 * </p>
 */
public class FullRandom {

    private static List<String> servers = new ArrayList<String>();
    private static Random random = new Random();

    static {
        servers.add("192.168.1.1");
        servers.add("192.168.1.2");
        servers.add("192.168.1.3");
        servers.add("192.168.1.4");
    }

    private static String go() {
        int number = random.nextInt(servers.size());
        return servers.get(number);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(go());
        }
    }

}
