package JAVA高并发编程详解.chapter05;

import java.util.LinkedList;
/**
 * notify方法只能唤醒其中的一个线程
 * （若干个线程调用了wait方法之后被加入了monitor关联的wait set中
 * 待另外一个线程调用该monitor的notify方法之后，），
 * 而notifyAll方法可以同时唤醒全部阻塞线程
 * 同样被唤醒的线程仍需要继续争抢monitor监视器
 *
 * 所以这个类需要将临界值的判断if更改为while，将notify更改为notifyAll即可
 */
import static java.lang.Thread.currentThread;

public class EventQueue {

    private final int max;

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }
    public EventQueue(int max) {
        this.max = max;
    }

    static class Event{}

    private final LinkedList<Event> eventQueue= new LinkedList<>();

    public void offer(Event event){
        synchronized (eventQueue){
            if(eventQueue.size() >= max){
                try {
                    console(" the queue is full.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            if(eventQueue.isEmpty()){
                try {
                    console(" the queue is empty.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console(" the event "+ event+" is handled.");

            return event;
        }
    }

    private void console(String message){
        System.out.printf("%s:%s\n",currentThread().getName(),message);
    }
}
