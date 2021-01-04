package com.jayden.balance.round;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangwei
 * @description 加权轮询负载均衡算法
 */
public class WeightRound {

    private static Map<String, Integer> servers = new HashMap<String, Integer>();
    private static int index;

    static {
        servers.put("192.168.1.1", 2);
        servers.put("192.168.1.2", 7);
        servers.put("192.168.1.3", 1);
    }


    private static String go() {
        int allWeight = servers.values().stream().mapToInt(a -> a).sum();
        int number = (index++) % allWeight;
        for (Map.Entry<String, Integer> item : servers.entrySet()) {
            if (item.getValue() > number) {
                return item.getKey();
            }
            number -= item.getValue();
        }
        return "";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(go());
        }
    }

}
