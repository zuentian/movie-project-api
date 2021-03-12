package JAVA高并发编程详解.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IntegerAccumlator {

    private int init;

    public IntegerAccumlator(int init){
        this.init = init;
    }

    public int add(int i){
        this.init += i;
        return this.init;
    }

    public int getValue(){
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumlator accumlator = new IntegerAccumlator(0);

        IntStream.range(0,3).forEach(i -> new Thread(() ->{
            int inc = 0;
            while (true){
                //首先获得old value
                /*int oldValue = accumlator.getValue();
                int result = accumlator.add(inc);*/
                int oldValue ;
                int result ;
                synchronized (IntegerAccumlator.class){
                    oldValue = accumlator.getValue();
                    result = accumlator.add(inc);
                }
                //System.out.println(oldValue + "+" + inc + "=" +result);

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
