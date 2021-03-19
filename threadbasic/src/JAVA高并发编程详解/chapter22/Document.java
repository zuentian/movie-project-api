package JAVA高并发编程详解.chapter22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.currentThread;

/**
 * Balking(犹豫)设计模式
 * 多个线程监控某个共享变量
 * A线程监控到共享变量发生变化后即将触发某个动作
 * 但是此时发现有另外一个线程B已经针对该变量的变化开始了行动
 * 因此A便放弃了准备开始的工作
 */
//代表正在编辑的文档类
public class Document {

    //如果文档发生改变，changed会被设置为true
    private boolean changed = false;

    //一次需要保存的内容，可以理解为内容缓存
    private List<String> context  = new ArrayList<>();

    private final FileWriter writer;

    private static AutoSaveThread autoSaveThread;

    //构造函数需要传入文档保存的路径和文档名称
    private Document(String documentPath,String documentName) throws IOException {
        this.writer = new FileWriter(new File(documentPath,documentName),true);
    }

    //静态方法，主要用于创建文档，顺便启动自动保存文档的线程
    public static Document create(String documentPath,String documentName) throws IOException {
        Document document = new Document(documentPath,documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    //save方法用于为外部显式进行文档保存
    public void save() throws IOException{
        synchronized (this){
            if(!changed){
                return;
            }
            System.out.println(currentThread()+" execute the save action");
            //将内容写入文档中
            for (String cacheLine : context){
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
            }
            this.writer.flush();
            this.changed = false;
            this.context.clear();
        }
    }

    //文档关闭的时候首先中断自动保存线程，然后关闭writer释放资源
    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }

    public void edit(String content){
        synchronized (this){
            this.context.add(content);
            this.changed = true;
        }
    }
}
