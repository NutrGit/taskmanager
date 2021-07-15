package test.impl;


import test.interfaces.Context;
import test.interfaces.ExecutionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExecutionManagerImpl implements ExecutionManager {

    private List<Runnable> taskList;
    private ExecutionStatisticsImpl statistics;

    public ExecutionManagerImpl() {

    }

    @Override
    public Context execute(Runnable... tasks) {

        taskList = Arrays.asList(tasks);
        statistics = new ExecutionStatisticsImpl();
        List<Integer> timeExecutionList = new ArrayList<>();
        ContextImpl context = new ContextImpl();

        Iterator<Runnable> runnableIterator = taskList.iterator();
        while (runnableIterator.hasNext()) {
            Runnable r = runnableIterator.next();
            if (r instanceof ThreadPimage) {
                ThreadPimage thread = (ThreadPimage) r;
                System.out.println("name = " + thread.getName() + " \t isAlive = " + thread.isAlive());
                r.run();
                timeExecutionList.add(thread.getTimeExecution());
            } else if (r instanceof ThreadMagicCube) {
                ThreadMagicCube thread = (ThreadMagicCube) r;
                System.out.println("name = " + thread.getName() + " \t isAlive = " + thread.isAlive());
                r.run();
                timeExecutionList.add(thread.getTimeExecution());
            }
        }

        statistics.setTimeExecutionList(timeExecutionList);


//        for (int t : timeExecutionList) {
//            System.out.println("t = " + t);
//        }

        context.setStatistics(statistics);
        return context;
    }


}
