package JAVA高并发编程详解.chapter23;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 程序员旅游线程
 */
public class ProgrammerTravel extends Thread {

    //门阀
    private final Latch latch;
    //程序员
    private final String programmer;
    //交通工具
    private final String transportation;

    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer +
                " start take the transportation ["+transportation+"]");
        int i = ThreadLocalRandom.current().nextInt(10);
        try {
            //程序员乘坐交通工具花费在路上的时间
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + " arrived by "+ transportation + "时间："+i);
        //完成任务时使计数器减一
        latch.countDown();
    }
}
