package test;

import processing.core.PApplet;
import processing.event.KeyEvent;
import test.impl.ExecutionManagerImpl;
import test.impl.ExecutionStatisticsImpl;
import test.interfaces.Context;
import test.interfaces.ExecutionStatistics;
import test.util.MagicCube;

public class App extends PApplet {
    private static Runnable[] runnables;
    private String contextInfo;
    private Context context;

    public static void main(String[] args) {
        App app = new App();
        app.main(app.getClass().getName());
    }

    @Override
    public void settings() {
        size(100, 100);
    }

    @Override
    public void setup() {
        frameRate(9999);
        runnables = new Runnable[3];

//        Thread thread0 = new ThreadMagicCube(() -> {
//        }, 4);
//        thread0.setName("magiccube-thread0");
//        runnables[0] = thread0;
//
//        Thread thread1 = new ThreadMagicCube(() -> {
//        }, 2);
//        thread1.setName("magiccube-thread1");
//        runnables[1] = thread1;
//
//        Thread thread2 = new ThreadMagicCube(() -> {
//        }, 2);
//        thread2.setName("magiccube-thread2");
//        runnables[2] = thread2;

        runnables[0] = () -> {
            long startTime = System.nanoTime();
            MagicCube magicCube = new MagicCube(3);
            magicCube.findMagicCube();
            long endTime = System.nanoTime();
            double res = (double) (endTime - startTime) / 1000;
            ExecutionManagerImpl.timeExecutionList.add((int) res);
            System.out.println("time = " + res);
        };
        runnables[1] = () -> {
            long startTime = System.nanoTime();
            MagicCube magicCube = new MagicCube(2);
            magicCube.findMagicCube();
            long endTime = System.nanoTime();
            double res = (double) (endTime - startTime) / 1000;
            ExecutionManagerImpl.timeExecutionList.add((int) res);
            System.out.println("time = " + res);
        };
        runnables[2] = () -> {
            long startTime = System.nanoTime();
            MagicCube magicCube = new MagicCube(4);
            magicCube.findMagicCube();
            long endTime = System.nanoTime();
            double res = (double) (endTime - startTime) / 1000;
            ExecutionManagerImpl.timeExecutionList.add((int) res);
            System.out.println("time = " + res);
        };

        ExecutionManagerImpl manager = new ExecutionManagerImpl();

        context = manager.execute(runnables);
        System.out.println(context.getStatistics().toString());
    }

    @Override
    public void draw() {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        ExecutionStatistics statistics = context.getStatistics();
        contextInfo = statistics.toString();
        System.out.println(contextInfo);
    }
}
