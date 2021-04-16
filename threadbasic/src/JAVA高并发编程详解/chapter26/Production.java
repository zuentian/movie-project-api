package JAVA高并发编程详解.chapter26;

/**
 * 传送带上的产品
 */
public class Production extends InstructionBook {

    //产品编号
    private final int prodID;

    public Production(int prodID) {
        this.prodID = prodID;
    }


    @Override
    protected void firstProcess() {
        System.out.println("execute the "+prodID+" first process");
    }

    @Override
    protected void secondProcess() {
        System.out.println("execute the "+prodID+" second process");
    }
}
