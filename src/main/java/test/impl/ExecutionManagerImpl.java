package test.impl;


import test.interfaces.Context;
import test.interfaces.ExecutionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExecutionManagerImpl implements ExecutionManager {
    private List<Runnable> taskList;

    private static volatile Integer completedTaskCount = 0;

    public ExecutionManagerImpl() {

    }

    @Override
    public Context execute(Runnable... tasks) {

        taskList = Arrays.asList(tasks);
        ExecutionStatisticsImpl statistics = new ExecutionStatisticsImpl();

        ContextImpl context = new ContextImpl();
        context.setRunnableList(taskList);

        context.timeExecutionList.add(0);

//        Iterator<Runnable> runnableIterator = taskList.iterator();
//        while (runnableIterator.hasNext()) {
//            Runnable r = runnableIterator.next();
//            Thread thread = new Thread(r);
//            thread.start();
//        }

        for (int j = 0; j < taskList.size() - 1; j++) {
            Runnable r = taskList.get(j);
            Thread thread = new Thread(r);
            thread.start();
        }

        statistics.setTimeExecutionList(context.timeExecutionList);
        context.setStatistics(statistics);
        return context;
    }

    public static Integer getCompletedTaskCount() {
        return completedTaskCount;
    }

    public static void upCompletedTaskCount() {
        ExecutionManagerImpl.completedTaskCount++;
    }
}