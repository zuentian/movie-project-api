package interrupt;
/*
如果在sleep状态下停止某一线程会进入catch语句，并且清除停止状态值，使之变成false
 */
public class Run1 {

    public static void main(String[] args) {

        MyThread1 thread = new MyThread1();
        thread.start();
        thread.interrupt();
        System.out.println("end!");
    }
}
