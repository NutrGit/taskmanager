package test.impl;


import test.interfaces.Context;
import test.interfaces.ExecutionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExecutionManagerImpl implements ExecutionManager {
    private List<Runnable> taskList;
    public static List<Integer> timeExecutionList = new ArrayList<>();

    public ExecutionManagerImpl() {

    }

    @Override
    public Context execute(Runnable... tasks) {

        taskList = Arrays.asList(tasks);
        ExecutionStatisticsImpl statistics = new ExecutionStatisticsImpl();

        ContextImpl context = new ContextImpl();

        timeExecutionList.add(0);

//        Iterator<Runnable> runnableIterator = taskList.iterator();
//        while (runnableIterator.hasNext()) {
//            Runnable r = runnableIterator.next();
//            if (r instanceof ThreadPimage) {
//                ThreadPimage thread = (ThreadPimage) r;
////                r.run();
//                System.out.println("name = " + thread.getName() + " \t isAlive = " + thread.isAlive());
//                thread.start();
//                timeExecutionList.add(thread.getTimeExecution());
//            } else if (r instanceof ThreadMagicCube) {
//                ThreadMagicCube thread = (ThreadMagicCube) r;
////                r.run();
//                System.out.println("name = " + thread.getName() + " \t isAlive = " + thread.isAlive());
//                thread.start();
//                timeExecutionList.add(thread.getTimeExecution());
//            }
//        }

        Iterator<Runnable> runnableIterator = taskList.iterator();
        while (runnableIterator.hasNext()) {
            Runnable r = runnableIterator.next();
            Thread thread = new Thread(r);
            thread.start();
        }

        statistics.setTimeExecutionList(timeExecutionList);
        context.setStatistics(statistics);
        return context;
    }

}