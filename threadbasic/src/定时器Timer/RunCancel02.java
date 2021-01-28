package 定时器Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Time类的cancel()方法的作用是将任务队列中全部任务清空
 * 并且进程被销毁
 */
public class RunCancel02 {
    private static Timer timer = new Timer();

    static public class MyTaskA extends TimerTask{

        @Override
        public void run() {
            System.out.println("A运行了！时间为："+new Date());
            timer.cancel();
        }
    }

    static public class MyTaskB extends TimerTask{
        @Override
        public void run() {
            System.out.println("B运行了！时间为："+new Date());
        }
    }

    public static void main(String[] args) {
        try {
            MyTaskA taskA = new MyTaskA();
            MyTaskB taskB = new MyTaskB();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = "2021-01-27 14:03:00";

            Date dateRef = sdf.parse(dateString);
            System.out.println("字符串时间："+dateRef.toString()+" 当前时间："+new Date().toLocaleString());
            timer.schedule(taskA,dateRef,4000);
            timer.schedule(taskB,dateRef,4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
