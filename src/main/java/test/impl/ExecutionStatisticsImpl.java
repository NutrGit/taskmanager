package test.impl;


import test.interfaces.ExecutionStatistics;

import java.util.Arrays;
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

    private int getMedian() {
        Integer[] timeArray = timeExecutionList.toArray(new Integer[timeExecutionList.size()]);
        Arrays.sort(timeArray);
        double median;
        if (timeArray.length % 2 == 0) {
            median = ((double) timeArray[timeArray.length / 2] + (double) timeArray[timeArray.length / 2 - 1]) / 2;
        } else {
            median = timeArray[timeArray.length / 2];
        }
        return (int) median;
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
