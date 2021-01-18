package 等待wait的条件发生变化;

public class Run {

    /*public static void main(String[] args) throws InterruptedException {
        String lock = new String ("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        ThreadSubtract subtract1 = new ThreadSubtract(subtract);
        subtract1.setName("subtract1Thread");
        subtract1.start();
        ThreadSubtract subtract2 = new ThreadSubtract(subtract);
        subtract2.setName("subtract2Thread");
        subtract2.start();
        Thread.sleep(1000);
        ThreadAdd add1 = new ThreadAdd(add);
        add1.setName("addThread");
        add1.start();

    }*/
    public static void main(String[] args) throws InterruptedException {
        String lock = new String ("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        ThreadSubtract subtract1 = new ThreadSubtract(subtract);
        subtract1.setName("subtract1Thread");
        subtract1.start();
        ThreadSubtract subtract2 = new ThreadSubtract(subtract);
        subtract2.setName("subtract2Thread");
        subtract2.start();
        Thread.sleep(2000);
        ThreadAdd add1 = new ThreadAdd(add);
        add1.setName("addThread");
        add1.start();

    }
}
