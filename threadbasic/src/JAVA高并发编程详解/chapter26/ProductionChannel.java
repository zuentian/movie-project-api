package JAVA高并发编程详解.chapter26;

//产品传送带，在传送带上除了负责产品加工的工人之外，还有在传送带上等待加工的产品
public class ProductionChannel {

    //传送带上最多可以有多少个待加工的产品
    private final static int MAX_PROD= 100;

    //主要用来存放待加工的产品，也就是传送带
    //private final Production[] productionQueue;

    //队列尾
    private int tail;
    //队列头
    private int head;
    //当前在流水线上有多少个待加工的产品
    private int total;

    //在流水线上工作的工人
    //private final Worker[] workers;

}
