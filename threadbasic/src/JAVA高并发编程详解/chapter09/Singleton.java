package JAVA高并发编程详解.chapter09;

/**
 * 类在初始化为类静态变量赋值的时候，是按照代码的顺序
 * 如果Singleton instance =new Singleton()在int x = 4 之前的话
 * 虽然instance的构造函数中把x由0变成1,但是初始化之后x会变成4，y则因为有
 */
public class Singleton {
    private static Singleton instance = new Singleton();//结果是0 1
    private static int x = 4;
    private static int y ;


    //private static Singleton instance = new Singleton();//结果是1 1


    private Singleton(){
        x++;
        y++;
        System.out.println("Singleton() x="+x);
        System.out.println("Singleton() y="+y);
    }

    private static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
