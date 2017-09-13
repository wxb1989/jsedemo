package com.jsedom.hadoopdemo;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/9/6.
 */
public class WordCountDemo {


    public static void main(String[] args) throws Exception {
        //输入路径
        Calendar c  = Calendar.getInstance();
        Long longs =c.getTimeInMillis();
        String dstInput = "hdfs://120.26.61.237:9000/data/data.txt";

        //输出路径，必须是不存在的，空文件加也不行。

        String dstOut = "hdfs://120.26.61.237:9000/output_"+longs;

        JobConf conf = new JobConf(WordCountDemo.class);
        conf.setJobName("word count mapreduce demo");

        conf.set("mapreduce.framework.name", "yarn");
        conf.setBoolean("mapreduce.app-submission.cross-platform", true);// 配置使用跨平台提交任务
        conf.set("fs.defaultFS", "hdfs://120.26.61.237:9000");//指定namenode
        conf.set("mapreduce.framework.name", "yarn");  // 指定使用yarn框架
        conf.set("yarn.resourcemanager.address", "http://120.26.61.237:9032"); // 指定resourcemanager
        conf.set("yarn.resourcemanager.scheduler.address", "http://120.26.61.237:9030");// 指定资源分配器
        conf.setMapperClass(WordCountMapper.class);
        conf.setReducerClass(WordCountReducer.class);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(conf, new Path(dstInput));
        FileOutputFormat.setOutputPath(conf, new Path(dstOut));

        RunningJob  jobS = JobClient.runJob(conf);
        System.out.printf("job  id =  "+jobS.getID());
    }
}

