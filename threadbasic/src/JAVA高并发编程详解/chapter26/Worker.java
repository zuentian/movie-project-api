package JAVA高并发编程详解.chapter26;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 流水线工人，不断地从流水线上提取产品然后进行再次加工
 *
 */
public class Worker extends Thread{

    private final ProductionChannel channel;

    //主要用于获取一个随机值，模拟加工一个产品需要消耗的时间
    private final static Random random = new Random(System.currentTimeMillis());


    public Worker(String wokerName , ProductionChannel channel) {
        super(wokerName);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            try {
                Production production = channel.takeProduction();
                System.out.println(getName() + " process the "+ production);
                //对产品进行加工
                production.create();
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
