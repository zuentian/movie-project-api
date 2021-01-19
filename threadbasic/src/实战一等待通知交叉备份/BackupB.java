package 实战一等待通知交叉备份;

public class BackupB extends Thread {
    private DBTools dbTools;
    public BackupB(DBTools dbTools){
        super();
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupB();
    }
}
