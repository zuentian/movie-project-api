package JAVA高并发编程详解.chapter24;

import JAVA高并发编程详解.chapter08.BasicThreadPool;
import JAVA高并发编程详解.chapter08.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *用来接收客户端的连接
 */
public class ChatServer {

    //服务端端口
    private final int port;

    //定义线程池
    private ThreadPool threadPool;

    //服务端Socket
    private ServerSocket serverSocket;

    //通过构造函数传入端口
    public ChatServer(int port){
        this.port = port;
    }

    public ChatServer(){
        this(13312);
    }

    public void startServer() throws IOException {
        this.threadPool = new BasicThreadPool(1,4,2,1000);
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);
        System.out.println("Chat server is started  and listen at port:"+port);
        this.listen();
    }

    private void listen() throws IOException {
        for(;;){
            Socket client = serverSocket.accept();
            this.threadPool.execute(new ClientHandle(client));
        }
    }
}
