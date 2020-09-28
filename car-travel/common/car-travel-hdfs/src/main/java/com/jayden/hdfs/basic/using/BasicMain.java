package com.jayden.hdfs.basic.using;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.File;

public class BasicMain {
    public static void main(String[] args) {
        // 创建一个Configuration对象
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.0.121:9000");
        try {
            FileSystem fs = FileSystem.get(configuration);
            fs.mkdirs(new Path("/doc"));
            fs.close();
        } catch (Exception e) {

        }
    }
}
