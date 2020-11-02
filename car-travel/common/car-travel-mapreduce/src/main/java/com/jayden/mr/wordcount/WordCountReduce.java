package com.jayden.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 词频统计Reduce阶段
 */
public class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    /**
     * @param key     hello
     * @param values  List<1,1,1....>
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable count : values) {
            sum = sum + count.get();
        }
        context.write(key, new IntWritable(sum));
    }
}
