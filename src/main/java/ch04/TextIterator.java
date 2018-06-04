package ch04;

import org.apache.hadoop.io.Text;

import java.nio.ByteBuffer;

/**
 * @author: Walter
 * @Date: 2018/6/4 17:19
 * @Description:
 */
public class TextIterator {

    public static void main(String[] args) {
        Text t = new Text("\u0041\u00DF\u6771\uD801\uDC00");

        ByteBuffer buf = ByteBuffer.wrap(t.getBytes(), 0, t.getLength());
        int cp;
        //bytesToCodePoint -1时检测到末尾
        while (buf.hasRemaining() && (cp = Text.bytesToCodePoint(buf)) != -1) {
            System.out.println(Integer.toHexString(cp));
        }
    }
}
