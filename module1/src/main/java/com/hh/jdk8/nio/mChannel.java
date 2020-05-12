package com.hh.jdk8.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Channel 只负责传输数据,不直接操作数据.操作数据只通过Buffer!
 *
 * @author HaoHao
 * @date 2020/4/23 4:07 下午
 */
public class mChannel {

    @Test
    public void openChannel() throws IOException {
        // 文件流打开通道
        FileInputStream fis = new FileInputStream(new File(""));
        FileChannel channel = fis.getChannel();
        // 静态放款获取通道
        FileChannel.open(Paths.get(""), StandardOpenOption.WRITE);

    }


}
