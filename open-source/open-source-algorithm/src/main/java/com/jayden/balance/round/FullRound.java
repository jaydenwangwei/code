package com.jayden.balance.round;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangwei
 * @description 完全轮询负载均衡算法
 */
public class FullRound {
    private static List<String> servers = new ArrayList<String>();
    private static int index;

    static {
        servers.add("192.168.1.1");
        servers.add("192.168.1.2");
        servers.add("192.168.1.3");
        servers.add("192.168.1.4");
    }

    private static String go() {
        if (index == servers.size()) {
            index = 0;
        }
        return servers.get(index++);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(go());
        }
    }
}
