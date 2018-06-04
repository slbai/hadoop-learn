package ch04;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

/**
 * @author: Walter
 * @Date: 2018/6/4 16:06
 * @Description:
 */
public class PooledStreamCompressor {

    public static void main(String[] args) throws ClassNotFoundException {
        String codecClassname = args[0];
        Class<?> aClass = Class.forName(codecClassname);

        Configuration conf = new Configuration();
        //获取codec
        CompressionCodec codec = (CompressionCodec)ReflectionUtils.newInstance(aClass,conf);
        //大量使用压缩解压缩的情况下，可以调用原生代码库，使用CodecPool
        Compressor compressor = null;
        try {
            compressor = CodecPool.getCompressor(codec);//获取linux平台自带压缩解压库，性能提升
            CompressionOutputStream out =
                    codec.createOutputStream(System.out, compressor);//将写入输出流的数据压缩
            IOUtils.copyBytes(System.in, out, 4096, false);//从标准输入读取数据
            out.finish();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CodecPool.returnCompressor(compressor);
        }
    }
}
