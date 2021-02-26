package JAVA高并发编程详解.chapter12;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键词只能修饰类变量和实例变量
 * 对于方法参数、局部变量以及实例常量，类变量都不能进行修饰
 */
public class VolatileFoo {

    final static int MAX = 5;

    static volatile int init_value = 0;

    public static void main(String[] args) {
        //启动一个Reader线程，当发现local_value和init_value不同时
        //则输出init_value被修改的信息

        new Thread(()->{
            int localValue = init_value;
            while (localValue <MAX){
                if(init_value != localValue){
                    System.out.printf("The init_value is updated to" +
                            "[%d]\n",init_value);
                    localValue = init_value;
                }
            }
        },"Reader").start();

        new Thread(()->{
            int localValue = init_value;
            while (localValue < MAX){
                //修改init_value
                System.out.printf("The init_value will be changed to" +
                        "[%d]\n",++localValue);
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Updater").start();
    }

}
