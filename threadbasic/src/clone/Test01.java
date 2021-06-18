package clone;

public class Test01 {


    public static void main(String[] args) {
        Print print  =new Print();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    print.printA();
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    print.printB();
                }
            }
        }).start();
    }

}


class Print{

    public boolean flag = false;

    public synchronized void printA(){
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        System.out.println("A");
        this.notifyAll();

    }

    public  synchronized void printB(){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = false;
        System.out.println("B");
        this.notifyAll();
    }
}
