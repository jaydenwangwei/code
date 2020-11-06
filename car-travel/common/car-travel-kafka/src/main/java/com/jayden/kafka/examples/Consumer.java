package com.jayden.kafka.examples;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer extends ShutdownableThread {
    private Logger LOGGER = Logger.getLogger(this.getClass());
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;

    private PrintWriter printWriter = null;
    private String lineSeparator = null;
    private int batchNum = 0;

    public Consumer(String topic, String groupId) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.201:9092");
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<Integer, String>(props);
        this.topic = topic;
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1));
        System.out.println("消费到消息数:" + records.count());
        if (records.count() > 0) {
            for (ConsumerRecord<Integer, String> record : records) {
                LOGGER.warn("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
                String value = record.value();
            }
        }
    }

    /**
     * 写入消息log
     *
     * @param msg 从kafka消费来的消息
     */
    protected void writeLog(String msg) {
        printWriter.write(msg + lineSeparator);
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }

    public static void main(String[] args) {
        //kafka主题
        String topic = "test_msg";
        //消费组id
        String groupId = "test001";

        Consumer consumer = new Consumer(topic, groupId);
        consumer.start();
    }
}
