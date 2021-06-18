package clone;


public class Main {

    public static volatile boolean flag = true;
    public static void main(String[] args)  {




        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true){
                    synchronized (this){
                        while (flag){

                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //System.out.println("正在唤醒1");
                        flag = true;
                        System.out.println("A");
                        this.notifyAll();

                    }


                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    synchronized (this){
                        while(!flag){
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //System.out.println("正在唤醒2");
                        flag = false;
                        System.out.println("B");
                        this.notifyAll();

                    }


                }
            }
        }).start();


    }


}
