package JAVA高并发编程详解.chapter10;

/**
 * 根加载器
 * 由C++编写的，主要负责虚拟机核心类库的加载
 * 比如整个java.lang包都是由根加载器所加载的
 */
public class BootStrapClassLoader {

    public static void main(String[] args) {
        System.out.println("Bootstrap:"+String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
