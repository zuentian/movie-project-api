package JAVA高并发编程详解.chapter26;

/**
 * 在流水线上需要被加工的产品，create作为一个模板方法，提供了加工产品的说明书
 * 该类代表着组装产品的说明书，其中经过流水线传送带的产品将通过create()方法进行加工
 *
 */
public abstract class InstructionBook {
    
    public final void create(){
        this.firstProcess();
        this.secondProcess();
    }

    protected abstract void firstProcess();
    protected abstract void secondProcess();
}
