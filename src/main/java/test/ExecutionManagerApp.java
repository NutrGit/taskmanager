package test;

import processing.core.PApplet;
import test.impl.ExecutionManagerImpl;
import test.impl.ThreadMagicCube;
import test.impl.ThreadPimage;
import test.interfaces.Context;

public class ExecutionManagerApp extends PApplet {
    private Runnable[] runnables;

    public static void main(String[] args) {
        ExecutionManagerApp app = new ExecutionManagerApp();
        app.main(app.getClass().getName());
    }

    @Override
    public void draw() {
        for (Runnable r : runnables) {
            if (r != null && r instanceof ThreadPimage) {
                ThreadPimage thread = (ThreadPimage) r;
                if (thread.getImage() != null) {
                    image(thread.getImage(), 0, 0);
                }
//                System.out.println(thread.getImage() + " \t time = " + thread.getTimeExecution());
            } else if (r != null && r instanceof ThreadMagicCube) {
                ThreadMagicCube thread = (ThreadMagicCube) r;
            }
        }
    }

    @Override
    public void settings() {
        size(1024, 768);
    }

    @Override
    public void setup() {
        frameRate(9999);

        runnables = new Runnable[4];

        Thread thread1 = new ThreadPimage(() -> {
            System.out.println("thread1");
        }, "https://1.bp.blogspot.com/-s3TxswyAegw/U4IGwpGjmlI/AAAAAAAAC9o/DLfJtqOd3ns/s1600/so-logo.jpg", this);
        thread1.setName("sof-thread");
        runnables[0] = thread1;

        Thread thread2 = new ThreadPimage(() -> {
            System.out.println("thread2");
        }, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/OpenCV_Logo_with_text_svg_version.svg/1200px-OpenCV_Logo_with_text_svg_version.svg.png",
                this);
        thread2.setName("opencv-thread");
        runnables[1] = thread2;

        Thread thread3 = new ThreadPimage(() -> {
            System.out.println("thread3");
        }, "https://images.wallpaperscraft.ru/image/kosmonavt_astronavt_skafandr_137404_3840x2400.jpg2", this);
        thread3.setName("4kimage-thread");
        runnables[2] = thread3;

        Thread thread4 = new ThreadMagicCube(() -> {
            System.out.println("thread4");
        }, 3);
        thread4.setName("magiccube-thread");
        runnables[3] = thread4;

        ExecutionManagerImpl manager = new ExecutionManagerImpl();

        Context context = manager.execute(runnables);

        System.out.println(context.getStatistics().toString());
    }
}
