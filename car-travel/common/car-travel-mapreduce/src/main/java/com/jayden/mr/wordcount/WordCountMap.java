package com.jayden.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 词频统计Map阶段
 */
public class WordCountMap extends Mapper<LongWritable, Text, Text, IntWritable> {
    /**
     * @param key     行号
     * @param value   行内容
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        for (String word : words) {
            // 输出
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
