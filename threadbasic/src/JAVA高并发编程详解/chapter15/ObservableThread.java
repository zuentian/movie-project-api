package JAVA高并发编程详解.chapter15;

public class ObservableThread<T> extends Thread  implements  Observable{


    private final TaskLifecycle<T> lifecycle;
    private final Task<T> task;

    private Cycle cycle;

    public ObservableThread(Task<T> task){
        this(new TaskLifecycle.EmptyLifecycle<>(),task);
    }

    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        if(task == null){
            throw new IllegalStateException("The task is required.");
        }
        this.lifecycle = lifecycle;
        this.task = task;
    }

    @Override
    public void run() {
        this.update(Cycle.STARTED,null,null);
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if(lifecycle == null){
            return;
        }

        try {
            switch (cycle){
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(),result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(),e);
                    break;
            }
        } catch (Exception ex) {
            if(cycle == Cycle.ERROR){
                throw ex;
            }
        }

    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
