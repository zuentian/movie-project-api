package JAVA高并发编程详解.chapter08;

public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message){
        super(message);
    }

}
