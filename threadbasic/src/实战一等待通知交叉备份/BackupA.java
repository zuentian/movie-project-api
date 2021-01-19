package 实战一等待通知交叉备份;

public class BackupA extends Thread {
    private DBTools dbTools;
    public BackupA(DBTools dbTools){
        super();
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupA();
    }
}
