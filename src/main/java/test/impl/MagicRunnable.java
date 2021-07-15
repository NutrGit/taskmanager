package test.impl;

import test.util.MagicCube;

public class MagicRunnable implements Runnable {
    private int timeExecution;
    private int n;
    private boolean isComplete = false;
    private boolean isFailed = false;
    private boolean isAlive = false;
    private boolean isAwait = true;
    private boolean isInterrupt = false;

    public MagicRunnable() {
        super();
    }

    public MagicRunnable(int n) {
        this.n = n;
    }

    @Override
    public void run() {

        try {
            if (!isInterrupt) {
                isAwait = false;
                isAlive = true;
                isComplete = false;
                isFailed = false;
                long startTime = System.nanoTime();

                MagicCube magicCube = new MagicCube(n);
                magicCube.findMagicCube();

                long endTime = System.nanoTime();
                double res = (double) (endTime - startTime) / 1000;
                timeExecution = (int) res;
                ContextImpl.timeExecutionList.add(timeExecution);
                isComplete = true;
                isAlive = false;
                isFailed = false;
                System.out.println("time = " + timeExecution);
            } else {
                isFailed = true;
                isAlive = false;
                isComplete = false;
            }
            checkFinish();
        } catch (InterruptedException e) {
            checkFinish();
            isFailed = true;
            isAlive = false;
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

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isAwait() {
        return isAwait;
    }

    public void setInterrupt(boolean interrupt) {
        isInterrupt = interrupt;
    }

    public boolean isInterrupt() {
        return isInterrupt;
    }

    private void checkFinish() {
        ContextImpl.n++;
        System.out.println("n = " + ContextImpl.n);
        if (ContextImpl.n == ContextImpl.taskSize - 1) {
            ContextImpl.callback = () -> {
                System.out.println("onFinish");
            };
            ContextImpl.callback.run();
        }
    }
}
