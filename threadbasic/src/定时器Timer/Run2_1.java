package 定时器Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Run2_1 {
    static public class MyTaskA extends TimerTask{
        @Override
        public void run() {
            try {
                System.out.println("A运行了！时间为："+new Date());
                Thread.sleep(5000);
                System.out.println("A结束了！时间为："+new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            MyTaskA task = new MyTaskA();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = "2021-01-27 10:58:00";
            Timer timer = new Timer();
            Date dateRef = sdf.parse(dateString);
            System.out.println("字符串时间："+dateRef.toString()+" 当前时间："
                    +new Date().toLocaleString());
            timer.schedule(task,dateRef,4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
