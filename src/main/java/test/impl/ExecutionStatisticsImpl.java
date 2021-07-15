package test.impl;


import test.interfaces.ExecutionStatistics;

import java.util.List;

public class ExecutionStatisticsImpl implements ExecutionStatistics {

    private List<Integer> timeExecutionList;

    public ExecutionStatisticsImpl() {

    }

    @Override
    public int getMinExecutionTimeInMs() {
        int min = 999999999;
        for (int t : timeExecutionList) {
            if (t < min) {
                min = t;
            }
        }
        return min;
    }

    @Override
    public int getMaxExecutionTimeInMs() {
        int max = -999999999;
        for (int t : timeExecutionList) {
            if (t > max) {
                max = t;
            }
        }
        return max;
    }

    @Override
    public int getAverageExecutionTimeInMs() {
        int avgTime = 0;

        for (int t : timeExecutionList) {
            avgTime += t;
        }
        avgTime = avgTime / timeExecutionList.size();
        return avgTime;
    }

    public void setTimeExecutionList(List<Integer> timeExecutionList) {
        this.timeExecutionList = timeExecutionList;
    }

    @Override
    public String toString() {
        return "ExecutionStatisticsImpl{" +
                "minMs=" + getMinExecutionTimeInMs() +
                ", maxMs=" + getMaxExecutionTimeInMs() +
                ", avgMs=" + getAverageExecutionTimeInMs() +
                '}';
    }
}
