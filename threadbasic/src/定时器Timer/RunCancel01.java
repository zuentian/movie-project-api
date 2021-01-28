package 定时器Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTask类的cancel()方法的作用是将自身从任务队列中清除
 * 其他任务不受影响
 */
public class RunCancel01 {
    static public class MyTaskA extends TimerTask{
        @Override
        public void run() {
            System.out.println("A运行了！时间为："+new Date());
            this.cancel();
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
            Timer timer = new Timer();
            Date dateRef = sdf.parse(dateString);
            System.out.println("字符串时间："+dateRef.toString()+" 当前时间："+new Date().toLocaleString());
            timer.schedule(taskA,dateRef,4000);
            timer.schedule(taskB,dateRef,4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
