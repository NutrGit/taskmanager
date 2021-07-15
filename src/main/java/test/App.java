package test;

import processing.core.PApplet;
import processing.event.KeyEvent;
import test.impl.ExecutionManagerImpl;
import test.impl.ExecutionStatisticsImpl;
import test.impl.MagicRunnable;
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

        runnables[0] = new MagicRunnable(3);
        runnables[1] = new MagicRunnable(2);
        runnables[2] = new MagicRunnable(10); //interrupt exception

        ExecutionManagerImpl manager = new ExecutionManagerImpl();

        context = manager.execute(runnables);
        System.out.println(context.getStatistics().toString());
    }

    @Override
    public void draw() {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        //check statistics
        ExecutionStatistics statistics = context.getStatistics();
        contextInfo = statistics.toString() + " \n " +
                "complete tasks = " + context.getCompletedTaskCount() + " \n " +
                "failed tasks = " + context.getFailedTaskCount();
        System.out.println(contextInfo);
    }
}
