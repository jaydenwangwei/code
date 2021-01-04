package com.jayden.balance.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author wangwei
 * @description 加权随机负载均衡算法
 */
public class WeightRandom {

    private static Map<String, Integer> servers = new HashMap<String, Integer>();
    private static Random random = new Random();

    static {
        servers.put("192.168.1.1", 2);
        servers.put("192.168.1.2", 7);
        servers.put("192.168.1.3", 1);
    }

    private static String goOne() {
        List<String> ipList = new ArrayList<String>();
        for (Map.Entry<String, Integer> item : servers.entrySet()) {
            for (int i = 0; i < item.getValue(); i++) {
                ipList.add(item.getKey());
            }
        }
        int allWeight = servers.values().stream().mapToInt(a -> a).sum();
        int number = random.nextInt(allWeight);
        return ipList.get(number);
    }

    private static String goTwo() {
        int allWeight = servers.values().stream().mapToInt(a -> a).sum();
        int number = random.nextInt(allWeight);
        for (Map.Entry<String, Integer> item : servers.entrySet()) {
            if (item.getValue() >= number) {
                return item.getKey();
            }
            number -= item.getValue();
        }
        return "";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(goOne());
        }
        System.out.println("====================");
        for (int i = 0; i < 15; i++) {
            System.out.println(goTwo());
        }
    }

}
