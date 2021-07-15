package test;

import test.impl.ExecutionManagerImpl;
import test.impl.ThreadMagicCube;
import test.interfaces.Context;

public class App {
    private static Runnable[] runnables;

    public static void main(String[] args) {

        runnables = new Runnable[3];

        Thread thread0 = new ThreadMagicCube(() -> {
        }, 4);
        thread0.setName("magiccube-thread0");
        runnables[0] = thread0;

        Thread thread1 = new ThreadMagicCube(() -> {
        }, 2);
        thread1.setName("magiccube-thread1");
        runnables[1] = thread1;

        Thread thread2 = new ThreadMagicCube(() -> {
        }, 2);
        thread2.setName("magiccube-thread2");
        runnables[2] = thread2;

        ExecutionManagerImpl manager = new ExecutionManagerImpl();

        Context context = manager.execute(runnables);

        System.out.println(context.getStatistics().toString());
    }
}
