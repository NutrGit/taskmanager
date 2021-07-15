package test.impl;

import test.util.MagicCube;

public class MagicRunnable implements Runnable {
    private int timeExecution;
    private int n;
    private boolean isComplete = false;
    private boolean isFailed = false;

    public MagicRunnable() {
        super();
    }

    public MagicRunnable(int n) {
        this.n = n;
    }

    @Override
    public void run() {

        try {
            long startTime = System.nanoTime();

            MagicCube magicCube = new MagicCube(n);
            magicCube.findMagicCube();

            long endTime = System.nanoTime();
            double res = (double) (endTime - startTime) / 1000;
            timeExecution = (int) res;
            ContextImpl.timeExecutionList.add(timeExecution);
            isComplete = true;
            isFailed = false;
            System.out.println("time = " + timeExecution);
        } catch (InterruptedException e) {
            isFailed = true;
            isComplete = false;
            e.printStackTrace();
        }
    }

    public int getTimeExecution() {
        return this.timeExecution;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean isFailed() {
        return isFailed;
    }
}
