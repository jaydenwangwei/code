package com.jayden.kafka.examples;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ExamplesCallBack implements Callback {
    private final long startTime;
    private final int key;
    private final String message;

    public ExamplesCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (recordMetadata != null) {
            System.out.println(
                    "message(" + key + ", " + message + ") sent to partition(" + recordMetadata.partition() +
                            "), " +
                            "offset(" + recordMetadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}
