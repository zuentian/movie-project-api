package executor;

public class Main {


    public static void main(String[] args) {

        ExecutorTest executorTest = new ExecutorTest();
        for (int i = 0; i < 7; i++) {
            executorTest.update(i+"");
        }
    }


}
