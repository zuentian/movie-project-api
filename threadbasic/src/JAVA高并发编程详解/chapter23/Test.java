package JAVA高并发编程详解.chapter23;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Latch latch = new CountDownLatch(4);

        new ProgrammerTravel(latch,"祖儿","飞行").start();
        new ProgrammerTravel(latch,"李儿","步行").start();
        new ProgrammerTravel(latch,"李智恩","跑步").start();
        new ProgrammerTravel(latch,"IU","划水").start();

        latch.await();
        System.out.println("==all of programer arrived ==");
    }
}
