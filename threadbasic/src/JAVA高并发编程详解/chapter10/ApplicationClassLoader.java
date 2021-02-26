package JAVA高并发编程详解.chapter10;

/**
 * 系统类加载器
 * 常见的加载器
 * 引入的第三方jar包
 */
public class ApplicationClassLoader {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
}
