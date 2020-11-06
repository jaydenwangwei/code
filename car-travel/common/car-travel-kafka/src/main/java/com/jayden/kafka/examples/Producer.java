package com.jayden.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer extends Thread {
    private static Logger LOGGER = Logger.getLogger(Producer.class);
    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private final Boolean isAsync;

    /**
     * @param topic
     * @param isAsync
     */
    public Producer(String topic, Boolean isAsync) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.201:9092");
        //props.put("acks", "all");
        //props.put("retries", 0);
        //props.put("batch.size", 16384);
        //props.put("linger.ms", 1);
        //props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        producer = new KafkaProducer<Integer, String>(props);
        this.topic = topic;
        this.isAsync = isAsync;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true) {
            String messageStr = "Message_" + messageNo;
            LOGGER.warn("message:" + messageStr);
            long startTime = System.currentTimeMillis();
            if (isAsync) {
                // 异步发送 Send asynchronously
                producer.send(new ProducerRecord<Integer, String>(topic,
                        messageStr), new ExamplesCallBack(startTime, messageNo, messageStr));
            } else {
                // 同步发送 Send synchronously
                try {
                    producer.send(new ProducerRecord<Integer, String>(topic, messageStr)).get();
                    LOGGER.warn("Sent message: (" + messageNo + ", " + messageStr + ")");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            ++messageNo;
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer("test_msg", false);
        producer.start();
    }
}
