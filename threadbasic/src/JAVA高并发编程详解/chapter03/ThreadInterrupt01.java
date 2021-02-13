package JAVA高并发编程详解.chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt01 {

    public static void main(String[] args) {
        System.out.println("Main thread is interrupted?"+Thread.interrupted());

        Thread.currentThread().interrupt();

        System.out.println("Main thread is interrupted?"+Thread.currentThread().isInterrupted());

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("I will be interrupted still.");
        }
    }
}
