package 实战一等待通知交叉备份;

/**
 * 创建20个线程，其中10个线程是将数据备份到A数据库中
 * 另外10个线程是将数据备份到B数据库中，并且备份A数据和备份B数据是交叉进行的
 */
public class Run {
    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 50; i++) {
            BackupB output = new BackupB(dbTools);
            output.start();
            BackupA input = new BackupA(dbTools);
            input.start();
        }
    }
}
