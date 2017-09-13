package com.jsedom.hadoopdemo.utils;
import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.mapred.JobConf;


/**
 * 对hdfs文件系统的一些基本操作
 * Created by Administrator on 2017/9/5.
 */
public class HdfsUtil {
    private static final String HDFS = "hdfs://120.26.61.237:9000/";
    // hdfs路径
    private String hdfsPath;
    // Hadoop系统配置
    private Configuration conf;

    public HdfsUtil(Configuration conf) {
        this(HDFS, conf);
    }

    public HdfsUtil(String hdfs, Configuration conf) {
        this.hdfsPath = hdfs;
        this.conf = conf;
    }

    // 启动函数
    public static void main(String[] args) throws IOException {
        JobConf conf = config();
        HdfsUtil hdfs = new HdfsUtil(conf);
        // 可以同时建多级目录
//         hdfs.mkdirs("/output");
        // hdfs.rmr("/new");
        // 可用当前eclipse工作空间的相对路径和文件绝对路径 以及当前项目的路径不加"/"
//         hdfs.copyFileToHdfs("data/hive.txt", "/data");
        // hdfs.copyFileToHdfs("/Xiaomi/MiFlashClean.cmd", "/data");
         hdfs.copyFileToHdfs("E:/hadoopuploaddata/data.txt", "/data");
//         hdfs.rmr("/data/catalina.txt");
        hdfs.ls("/data");
        // hdfs.rmr("/data/user_pay_201606");
//         hdfs.createFileOnHDFS("/data/createTest.txt");
        // hdfs.download("/data/RecommendList", "C:/Users/Skye/Desktop");
        // hdfs.cat("/data/RecommendList1");
        // hdfs.renameFile("/data/RecommendList", "/data/RecommendListOld");
        // hdfs.ls("/data");
        //hdfs.findLocationOnHadoop("/data/RecommendListOld");
    }

    // 加载Hadoop配置文件
    public static JobConf config() {
        JobConf conf = new JobConf(HdfsUtil.class);
        conf.setJobName("HdfsUtil");
//         conf.addResource("classpath:/hadoop/core-site.xml");
//         conf.addResource("classpath:/hadoop/hdfs-site.xml");
//         conf.addResource("classpath:/hadoop/mapred-site.xml");
        return conf;
    }

    public void mkdirs(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        if (!fs.exists(path)) {
            fs.mkdirs(path);
            System.out.println("Create: " + folder);
        }
        fs.close();
    }



    public void rmr(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        fs.deleteOnExit(path);
        System.out.println("Delete: " + folder);
        fs.close();
    }

    public void ls(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        FileStatus[] list = fs.listStatus(path);
        System.out.println("ls: " + folder);
        System.out.println("==========================================================");
        for (FileStatus f : list) {
            System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), f.getLen());
        }
        System.out.println("==========================================================");
        fs.close();
    }

    /**
     * 给定文件名和文件内容，创建hdfs文件
     *
     * @param dst
     * @param content
     * @throws IOException
     */
    public void createFile(String dst,String content)
            throws IOException {
        Path dstPath = new Path(dst);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath+dstPath), conf);
        FSDataOutputStream outputStream = fs.create(dstPath);
        fs.createNewFile(dstPath);
        try {
            byte[] buff = content.getBytes();
            outputStream.write(buff, 0, buff.length);
            System.out.println("create file " + dst + " success!");
        }finally {
            if (outputStream != null)
                outputStream.close();
        }
        fs.close();
    }
    /**
     * 在HDFS上创建文件
     *
     * @param filePath
     *            创建的文件的名称，HDFS目录
     * @throws IOException
     */
    public  void createFileOnHDFS(String filePath) throws IOException {
        FileSystem hdfs = FileSystem.get(URI.create(hdfsPath+filePath), conf);
        Path newFile = new Path(filePath);
        hdfs.createNewFile(newFile);
        // 释放资源
        hdfs.close();
        System.out.println("创建文件成功");
    }

    public void copyFileToHdfs(String local, String remote) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        fs.copyFromLocalFile(new Path(local), new Path(remote));
        System.out.println("copy from: " + local + " to " + remote);
        fs.close();
    }

    public void download(String remote, String local) throws IOException {
        Path path = new Path(remote);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        fs.copyToLocalFile(path, new Path(local));
        System.out.println("download: from" + remote + " to " + local);
        fs.close();
    }

    public void renameFile(String oldFileName, String newFileName) throws IOException {
        boolean isSuccess = true;

        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        try {
            isSuccess = fs.rename(new Path(oldFileName), new Path(newFileName));
        } catch (IOException e) {
            isSuccess = false;
        }
        System.out.println(isSuccess ? "Rename success！ " + oldFileName + " to " + newFileName
                : "Rename failed！" + oldFileName + " to " + newFileName);
        fs.close();
    }

    /**
     * 查看某个文件在HDFS集群的位置
     *
     * @throws IOException
     */

    public void findLocationOnHadoop(String filePath) throws IOException {
        // Path targetFile=new Path(rootPath+"user/hdfsupload/AA.txt");
        // FileStatus fileStaus=coreSys.getFileStatus(targetFile);
        Path targetFile = new Path(HDFS + filePath);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        FileStatus fileStaus = fs.getFileStatus(targetFile);
        BlockLocation[] bloLocations = fs.getFileBlockLocations(fileStaus, 0, fileStaus.getLen());
        for (int i = 0; i < bloLocations.length; i++) {
            System.out.println("block_" + i + "_location:" + bloLocations[i].getHosts()[0]);
        }
        fs.close();
    }

    public void cat(String remoteFile) throws IOException {
        Path path = new Path(remoteFile);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        FSDataInputStream fsdis = null;
        System.out.println("cat: " + remoteFile);
        try {
            fsdis = fs.open(path);
            IOUtils.copyBytes(fsdis, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(fsdis);
            fs.close();
        }
    }

}
