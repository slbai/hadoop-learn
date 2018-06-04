package ch03;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

/**
 * @author: Walter
 * @Date: 2018/6/4 11:59
 * @Description:
 */
public class FileCopyWithProgress {
    public static void main(String[] args) throws IOException {
        String local = args[0];
        String dst = args[1];
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(local));

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst),conf);
        FSDataOutputStream out = fs.create(new Path(dst),new Progressable() {
            public void progress() {
                System.out.println(".");
            }
        });

        IOUtils.copyBytes(in,out,2048,true);
    }
}
