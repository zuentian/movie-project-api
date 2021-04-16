package JAVA高并发编程详解.chapter28;

import JAVA高并发编程详解.chapter19.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {
    @Override
    protected void finish(T result) {
        super.finish(result);
    }
}
