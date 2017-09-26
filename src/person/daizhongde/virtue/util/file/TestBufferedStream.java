
package person.daizhongde.virtue.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestBufferedStream {

    public static void main(String[] args) throws Exception {
        // 指定要读取文件的缓冲输入字节流
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("F:\\test.jpg"));
        File file = new File("E:\\test.jpg");
        if (file != null) {
            file.createNewFile();
        }
        // 指定要写入文件的缓冲输出字节流
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bb = new byte[1024];// 用来存储每次读取到的字节数组
        int n;// 每次读取到的字节数组的长度
        while ((n = in.read(bb)) != -1) {
            out.write(bb, 0, n);// 写入到输出流
        }
        out.close();// 关闭流
        in.close();
    }

}

