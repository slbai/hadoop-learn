package ch03;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Walter
 * @Date: 2018/5/31 16:06
 * @Description:
 */
public class Cat {
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    public static void main(String[] args) throws Exception {
        InputStream in = new URL(args[0]).openStream();
        IOUtils.copyBytes(in,System.out,4096,false);
        IOUtils.closeStream(in);
    }
}
