package 实战一等待通知交叉备份;

public class DBTools {
    //volatile关键字的作用是强制从公共堆栈中取得变量的值
    //而不是从线程私有数据栈中取得变量的值
    //所以用server模式(相反的是client模式)启动的话，会从私有数据栈中取变量（这也就是server模式启动慢，但运行快），导致程序死掉
    //但是本地运行的话不是很明显
    volatile private boolean prevIsA = false;

    synchronized public void backupA(){
        try {
            while(prevIsA == true){
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("--------------");
            }
            prevIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public void backupB(){
        try {
            while (prevIsA == false){
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("+++++++++++++++++");
            }
            prevIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
