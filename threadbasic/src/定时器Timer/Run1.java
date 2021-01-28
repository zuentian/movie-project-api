package 定时器Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 执行任务的时间晚于当前时间：在未来执行的效果
 * 任务执行完了，但进程还未销毁
 */
public class Run1 {

    private static Timer timer = new Timer();
    static public class MyTask extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行了！时间为："+new Date());
        }


    }

    public static void main(String[] args) {
        try {
            MyTask task = new MyTask();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = "2021-01-27 10:29:00";
            Date dateRef = sdf.parse(dateString);
            System.out.println("字符串时间："+dateRef.toLocaleString()+" 当前时间："+new Date().toString());
            timer.schedule(task,dateRef);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
