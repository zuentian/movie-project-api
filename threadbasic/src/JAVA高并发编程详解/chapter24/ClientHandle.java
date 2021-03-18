package JAVA高并发编程详解.chapter24;

import java.io.BufferedReader;
import java.net.Socket;

public class ClientHandle implements Runnable {

    //客户端的socket连接
    private final Socket socket;
    //客户端的identity
    private final String clientIdentity;

    //通过构造函数传入客户端连接
    public ClientHandle(final Socket socket) {
        this.socket = socket;
        this.clientIdentity = socket.getInetAddress().getHostAddress()+":"+socket.getPort();
    }

    @Override
    public void run() {
        try {
            this.chat();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chat(){
        //BufferedReader bufferedReader =
    }
}
