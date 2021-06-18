package clone;

public class Test02 {

    public static boolean flag = false;

    public static volatile  int a = 0;

    public static volatile  int b = 0;

    public static Object object = new Object();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                   synchronized (object){
                       if (flag) {
                           try {
                               object.wait();
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                       flag = true;
                       a++;
                       System.out.println("A="+a);
                       object.notifyAll();
                   }
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    synchronized (object){
                        if (!flag){
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        flag = false;
                        b++;
                        System.out.println("B="+b);
                        object.notifyAll();
                    }
                }
            }
        }).start();
    }



}
