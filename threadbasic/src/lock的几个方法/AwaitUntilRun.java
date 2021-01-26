package lock的几个方法;

public class AwaitUntilRun {
    public static void main(String[] args) {
        AwaitUntilService service = new AwaitUntilService();
        AwaitUntilThreadA threadA = new AwaitUntilThreadA(service);
        threadA.start();
    }

}
