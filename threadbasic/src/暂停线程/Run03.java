package 暂停线程;

public class Run03 {

    public static void main(String[] args) {

        try {
            final MyObject myObject = new MyObject();
            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    myObject.setValue("a","aa");
                }
            };
            thread1.setName("a");
            thread1.start();
            Thread.sleep(500);
            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    myObject.printUsernamePassword();
                }
            };
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
