package synchronized同步语句块;

import synchronized方法与锁对象.ThreadB;

public class Task {

    private String getData1;
    private String getData2;

    public  void doLongTimeTask(){
        try {
            System.out.println("begin task");
            Thread.sleep(3000);
            String privateGetData1 = "长时间处理任务后从远处返回的值1 threadName="
                    +Thread.currentThread().getName();
            String privateGetData2 = "长时间处理任务后从远处返回的值2 threadName="
                    +Thread.currentThread().getName();
            synchronized (this){
                getData1 = privateGetData1;
                getData2 = privateGetData2;
            }
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
