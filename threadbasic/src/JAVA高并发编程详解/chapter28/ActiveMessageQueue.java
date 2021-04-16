package JAVA高并发编程详解.chapter28;

import java.util.LinkedList;

public class ActiveMessageQueue {


    private final LinkedList<MethodMessage> messages = new LinkedList<>();

    public ActiveMessageQueue(){
        //启动Worker线程
        new ActiveDaemonThread(this).start();
    }


    public void offer(MethodMessage methodMessage) {
        synchronized (this){
            messages.addLast(methodMessage);
            this.notify();
        }
    }

    public MethodMessage take(){
        synchronized (this){
            while (messages.isEmpty()){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("messages isNotEmpty");
            return messages.removeFirst();
        }
    }
}
