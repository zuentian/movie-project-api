package JAVA高并发编程详解.chapter01.Thread模拟营业大厅叫号机程序;

public class TicketWindow extends Thread {
    //柜面名称
    private final String name;

    //最多受理50笔业务
    private static final int MAX = 50;
    public TicketWindow(String name) {
        this.name = name;
    }

    private static int index = 1;
    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("柜面：" + name + "当前的号码："+(index++) );
        }

    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("1号出号机");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("2号出号机");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("3号出号机");
        ticketWindow3.start();
        TicketWindow ticketWindow4 = new TicketWindow("4号出号机");
        ticketWindow4.start();
    }


}
