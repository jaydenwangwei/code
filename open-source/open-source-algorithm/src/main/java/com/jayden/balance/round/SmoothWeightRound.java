package com.jayden.balance.round;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangwei
 * @description 平滑加权轮询负载均衡算法
 */
public class SmoothWeightRound {
    private static Map<String, Server> servers = new HashMap<String, Server>();

    static {
        servers.put("192.168.1.1", new Server(5, 5, "192.168.1.1"));
        servers.put("192.168.1.2", new Server(1, 1, "192.168.1.2"));
        servers.put("192.168.1.3", new Server(1, 1, "192.168.1.3"));
    }

    public static String go() {
        Server maxWeightServer = null;
        int allWeight = servers.values().stream().mapToInt(Server::getWeight).sum();
        for (Map.Entry<String, Server> item : servers.entrySet()) {
            Server currentServer = item.getValue();
            if (maxWeightServer == null || currentServer.getCurrentWeight() > maxWeightServer.getCurrentWeight()) {
                maxWeightServer = currentServer;
            }
        }
        assert maxWeightServer != null;
        maxWeightServer.setCurrentWeight(maxWeightServer.getCurrentWeight() - allWeight);

        for (Map.Entry<String, Server> item : servers.entrySet()) {
            Server currentServer = item.getValue();
            currentServer.setCurrentWeight(currentServer.getCurrentWeight() + currentServer.getWeight());
        }
        return maxWeightServer.getIp();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(go());
        }
    }
}
