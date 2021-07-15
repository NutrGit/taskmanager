package test.impl;

import test.interfaces.Context;
import test.interfaces.ExecutionStatistics;

public class ContextImpl implements Context {

    private ExecutionStatistics statistics;

    public ContextImpl() {
    }

    @Override
    public int getCompletedTaskCount() {
        return 0;
    }

    @Override
    public int getFailedTaskCount() {
        return 0;
    }

    @Override
    public int getInterruptedTaskCount() {
        return 0;
    }

    @Override
    public void interrupt() {

    }

    @Override
    public boolean isFinished() {
        return false;
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
}
