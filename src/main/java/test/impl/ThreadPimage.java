package test.impl;

import processing.core.PApplet;
import processing.core.PImage;

public class ThreadPimage extends Thread {
    private int timeExecution;
    private PImage image;
    private String url;
    private PApplet pApplet;

    public ThreadPimage(Runnable runnable) {
        super(runnable);
    }

    public ThreadPimage(Runnable runnable, String url, PApplet pApplet) {
        super(runnable);
        this.url = url;
        this.pApplet = pApplet;
    }

    @Override
    public void run() {
        super.run();
        long startTime = System.nanoTime();
        image = pApplet.loadImage(url);
        long endTime = System.nanoTime();
        double res = (endTime - startTime) / 1000;
        timeExecution = (int) res;
    }

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public int getTimeExecution() {
        return timeExecution; //ms
    }

    public void setTimeExecution(int timeExecution) {
        this.timeExecution = timeExecution;
    }
}
