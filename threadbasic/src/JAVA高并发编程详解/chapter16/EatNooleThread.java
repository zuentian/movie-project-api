package JAVA高并发编程详解.chapter16;

public class EatNooleThread extends Thread{

    private final String name;

    private final Tableware leftTool;

    private final Tableware rightTool;

    public EatNooleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (leftTool) {
                System.out.println(name + " take up " + leftTool + "(left)");
                synchronized (rightTool) {
                    System.out.println(name + " take up " + rightTool + "(right)");
                    System.out.println(name + " is eating now.");
                    System.out.println(name + " put down " + rightTool + "(right)");
                }
                System.out.println(name + " put down " + leftTool + "(left)");
            }
        }
    }

    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new EatNooleThread("A",fork,knife).start();
        new EatNooleThread("B",fork,knife).start();
    }
}
