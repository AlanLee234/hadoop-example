package com.alan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@PropertySource("classpath:application.properties")
@Component
public class HDFSOperation {
    private Configuration conf;
    private FileSystem fs;

    @Value("${hdfs.path}")
    private String hdfsPath;


    public String getHdfsPath() {
        return this.hdfsPath;
    }

    public String getDfsPath(){
        return conf.get("fs.defaultFS");
    }



    public HDFSOperation(@Value("${hdfs.path}") String hdfsPath) {
        conf = new Configuration();
//        String pathtemp = "hdfs://10.10.10.96:9000";
//        conf.addResource(new Path("core-site.xml"));
//        fs = FileSystem.get(conf);
        conf.set("fs.defaultFS", hdfsPath);
        try {
            fs = FileSystem.get(new URI(hdfsPath), conf,"hadoop");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean mkdir(String dfsPath){
        Path path = new Path(dfsPath);
        try {
            if(!fs.exists(path)){
                fs.mkdirs(path);
            }
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean upload(String inputPath, String dfsPath){
        boolean ok = false;
        try {
            InputStream in = new FileInputStream(inputPath);
            ok = upload(in, dfsPath);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return ok;
    }

    public boolean upload(InputStream in, String dfsPath){

        Path p = new Path(dfsPath);
        try {
//            fs = FileSystem.get(conf);
//            fs.getConf().setLong("fs.local.block.size", blocksize);
            if(fs.exists(p)){
                System.out.println("The file already exists!");
                return false;
            }
//            FSDataOutputStream out = fs.create(p, progress);
//            FSDataOutputStream out = fs.create(p, true, fs.getConf().getInt("io.file.buffer.size", 4096), fs.getDefaultReplication(), blocksize, progress);
            FSDataOutputStream out = fs.create(p, true);
            IOUtils.copyBytes(in, out, conf);

            byte[] buffer = new byte[4096];
            int length = 0;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
