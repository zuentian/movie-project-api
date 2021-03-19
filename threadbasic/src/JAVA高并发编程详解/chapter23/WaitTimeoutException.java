package JAVA高并发编程详解.chapter23;

public class WaitTimeoutException extends Exception {

    public WaitTimeoutException(String message){
        super(message);
    }
}
