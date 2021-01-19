package 管道通信字节流;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Run {
    public static void main(String[] args) throws IOException, InterruptedException {
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();
        outputStream.connect(inputStream);//两个Stream之间产生通信连接
        ThreadRead threadRead = new ThreadRead(readData,inputStream);
        threadRead.start();
        Thread.sleep(2000);
        ThreadWrite threadWrite = new ThreadWrite(writeData,outputStream);
        threadWrite.start();
    }
}
