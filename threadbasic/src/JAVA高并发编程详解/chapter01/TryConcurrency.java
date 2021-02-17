package JAVA高并发编程详解.chapter01;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {

    public static void main(String[] args) {
        /*new Thread(){
            @Override
            public void run() {
                enjoyMusic();
            }
        }.start();*/
        new Thread(TryConcurrency::enjoyMusic).start();//Java 8 Lambda改造上面的代码
        browseNews();
    }

    private static void browseNews() {

        for (;;) {
            System.out.println("Uh-huh,the good news.");
            sleep(1);
        }

    }

    private static void enjoyMusic(){
        for (;;){
            System.out.println("Uh-huh,the nice music.");
            sleep(1);
        }
    }

    private static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
