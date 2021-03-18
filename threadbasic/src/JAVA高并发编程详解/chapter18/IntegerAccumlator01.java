package JAVA高并发编程详解.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 重构后的IntegerAccumulator
 * 使用了final修饰其的目的是为了防止由于继承重写而导致失去线程安全性
 * 另外init属性被final修饰不允许线程对其进行改变
 * 在构造函数中复制后将不会再改变
 * add方法并未在原有init的基础上进行累加，而是创建了一个全新的IntegerAccumulator，并未
 * 提供任何修改原始IntegerAccumulator的机会，运行上面的程序不会出现ERROR的情况
 */
public final class IntegerAccumlator01 {

    private final int init;

    public IntegerAccumlator01 ( int init){
        this.init = init;
    }

    public IntegerAccumlator01(IntegerAccumlator01 accumlator01,int init){
        this.init = accumlator01.getValue() + init;
    }

    //每次相加都会产生一个新的IntegerAccumulator
    public IntegerAccumlator01 add(int i){
        return new IntegerAccumlator01(this,i);
    }

    public int getValue() {
        return this.init;
    }
    public static void main(String[] args) {
        IntegerAccumlator accumlator = new IntegerAccumlator(0);

        IntStream.range(0,3).forEach(i -> new Thread(() ->{
            int inc = 0;
            while (true){
                //首先获得old value
                int oldValue = accumlator.getValue();
                int result = accumlator.add(inc);
                if (inc + oldValue != result){
                    System.err.println("ERROR:"+oldValue+"+"+inc+"="+result);
                }
                inc ++;
                slowly();
            }
        }).start());
    }
    private static void slowly(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
