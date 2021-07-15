package test.interfaces;

public interface ExecutionManager {
    Context execute(Runnable... tasks);
}
