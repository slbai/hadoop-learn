package ch03;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

/**
 * @author: Walter
 * @Date: 2018/6/4 11:47
 * @Description:
 */
public class DoubleCat {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(args[0]),conf);
        FSDataInputStream in = fs.open(new Path(args[0]));
        IOUtils.copyBytes(in,System.out,2048);
        in.seek(0);
        IOUtils.copyBytes(in,System.out,2048);
        IOUtils.closeStream(in);
    }
}
