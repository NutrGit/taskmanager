package test.interfaces;

public interface Context {
    //Метод getCompletedTaskCount() возвращает количество тасков, которые на текущий момент успешно выполнились.
    int getCompletedTaskCount();

    //Метод getFailedTaskCount() возвращает количество тасков, при выполнении которых произошел Exception.
    int getFailedTaskCount();

    //Метод interrupt() отменяет выполнения тасков, которые еще не начали выполняться.
    int getInterruptedTaskCount();

    //Метод getInterruptedTaskCount() возвращает количество тасков, которые не были выполнены из-за отмены
    // (вызовом предыдущего метода).
    void interrupt();

    //Метод isFinished() вернет true, если все таски были выполнены или отменены, false в противном случае.
    boolean isFinished();

    //onFinish(Runnable callback) - После завершения всех тасков должен выполниться callback (ровно 1 раз).
    void onFinish(Runnable callback);

    //Метод getStatistics() возвращает статистиску по времени выполнения задач.
    ExecutionStatistics getStatistics();

    //awaitTermination() - блокирует текущий поток, из которого произошел вызов,
    // до тех пор пока не выполнятся все задачи.
    void awaitTermination();


}
