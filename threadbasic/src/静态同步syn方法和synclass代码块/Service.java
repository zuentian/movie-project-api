package 静态同步syn方法和synclass代码块;

public class Service {
    synchronized public static void printA(){
        try {
            System.out.println("线程名称为："+Thread.currentThread().getName()
                        +"在"+System.currentTimeMillis()+"进入printA");
            Thread.sleep(3000);
            System.out.println("线程名称为："+Thread.currentThread().getName()
                    +"在"+System.currentTimeMillis()+"离开printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public static void printB(){
        try {
            System.out.println("线程名称为："+Thread.currentThread().getName()
                    +"在"+System.currentTimeMillis()+"进入printB");
            Thread.sleep(3000);
            System.out.println("线程名称为："+Thread.currentThread().getName()
                    +"在"+System.currentTimeMillis()+"离开printB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public void printC(){
        try {
            System.out.println("线程名称为："+Thread.currentThread().getName()
                    +"在"+System.currentTimeMillis()+"进入printC");
            Thread.sleep(3000);
            System.out.println("线程名称为："+Thread.currentThread().getName()
                    +"在"+System.currentTimeMillis()+"离开printC");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
