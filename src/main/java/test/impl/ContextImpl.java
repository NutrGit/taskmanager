package test.impl;

import test.interfaces.Context;
import test.interfaces.ExecutionStatistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContextImpl implements Context {

    private ExecutionStatistics statistics;
    private volatile int completedTaskCount;
    private volatile int failedTaskCount;
    private volatile int interruptedTaskCount;
    public static List<Integer> timeExecutionList = new ArrayList<>();
    private List<Runnable> runnableList = new ArrayList<>();
    public static Runnable callback;
    public static volatile int taskSize = 0;
    public static volatile int n = 0;


    public ContextImpl() {
    }


    @Override
    public int getCompletedTaskCount() {
        completedTaskCount = 0;
        Iterator<Runnable> runnableIterator = runnableList.iterator();
        while (runnableIterator.hasNext()) {
            Runnable runnable = runnableIterator.next();
            if (runnable instanceof MagicRunnable) {
                MagicRunnable magicRunnable = (MagicRunnable) runnable;
                if (magicRunnable.isComplete()) {
                    completedTaskCount++;
                }
            }
        }

        return completedTaskCount;
    }

    @Override
    public int getFailedTaskCount() {
        failedTaskCount = 0;
        Iterator<Runnable> runnableIterator = runnableList.iterator();
        while (runnableIterator.hasNext()) {
            Runnable runnable = runnableIterator.next();
            if (runnable instanceof MagicRunnable) {
                MagicRunnable magicRunnable = (MagicRunnable) runnable;
                if (magicRunnable.isFailed()) {
                    failedTaskCount++;
                }
            }
        }
        return failedTaskCount;
    }

    @Override
    public int getInterruptedTaskCount() {
        interruptedTaskCount = 0;
        Iterator<Runnable> runnableIterator = runnableList.iterator();
        while (runnableIterator.hasNext()) {
            Runnable runnable = runnableIterator.next();
            if (runnable instanceof MagicRunnable) {
                MagicRunnable magicRunnable = (MagicRunnable) runnable;
                if (magicRunnable.isInterrupt()) {
                    interruptedTaskCount++;
                }
            }
        }
        return interruptedTaskCount;
    }

    @Override
    public void interrupt() {
        Iterator<Runnable> runnableIterator = runnableList.iterator();
        while (runnableIterator.hasNext()) {
            Runnable runnable = runnableIterator.next();
            if (runnable instanceof MagicRunnable) {
                MagicRunnable magicRunnable = (MagicRunnable) runnable;
                if (magicRunnable.isAwait()) {
                    magicRunnable.setInterrupt(true);
                }
            }
        }
    }

    @Override
    public boolean isFinished() {
        int aliveTasks = 0;
        Iterator<Runnable> runnableIterator = runnableList.iterator();
        while (runnableIterator.hasNext()) {
            Runnable runnable = runnableIterator.next();
            if (runnable instanceof MagicRunnable) {
                MagicRunnable magicRunnable = (MagicRunnable) runnable;
                if (magicRunnable.isAlive()) {
                    aliveTasks++;
                }
            }
        }

        return !(aliveTasks > 0);
    }

    @Override
    public void onFinish(Runnable callback) {

    }

    @Override
    public ExecutionStatistics getStatistics() {
        return this.statistics;
    }

    @Override
    public void awaitTermination() {

    }

    public void setStatistics(ExecutionStatistics statistics) {
        this.statistics = statistics;
    }

    public List<Runnable> getRunnableList() {
        return runnableList;
    }

    public void setRunnableList(List<Runnable> runnableList) {
        taskSize = runnableList.size();
        this.runnableList = runnableList;
    }
}
