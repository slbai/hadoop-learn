package ch04;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

/**
 * @author: Walter
 * @Date: 2018/6/4 16:25
 * @Description: 压缩从标准输入读取的数据，然后将其输出到out
 */
public class StreamCompressor {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String codecClass = args[0];
        Class<?> aClass = Class.forName(codecClass);
        Configuration conf = new Configuration();

        CompressionCodec codec = (CompressionCodec)
                ReflectionUtils.newInstance(aClass, conf);

        CompressionOutputStream out = codec.createOutputStream(System.out);
        IOUtils.copyBytes(System.in, out, 4096, false);
        out.finish();
    }
}
