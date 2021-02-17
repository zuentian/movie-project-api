package 定时器Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 使用scheduleAtFixedRate方法
 * 如果执行任务的时间没有被延时，则下一次执行任务的时间是上一次任务的开始时间加上延迟时间
 * 这个方法具有追赶性,如果计划时间早于当前时间，就会把计划时间和当前时间段的任务执行
 */
public class RunScheduleAtFixedRate {
    private static Timer timer = new Timer();

    private static int runCount = 0;

    static public class MyTask1 extends TimerTask{
        @Override
        public void run() {
            try {
                System.out.println("1 begin 运行了！时间为：" + new Date());
                Thread.sleep(4000);

                System.out.println("1   end 运行了！时间为："+new Date());
                runCount++;
                if(runCount == 5){
                    timer.cancel();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            MyTask1 task1 = new MyTask1();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = "2021-01-27 14:21:00";
            Date dateRef = sdf.parse(dateString);
            timer.scheduleAtFixedRate(task1,dateRef,7000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
