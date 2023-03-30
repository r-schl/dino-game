package structure;

import enums.State;
import game.Game;
import ui.Gui;

import static java.lang.Thread.sleep;

public class Loop implements Runnable {
    public static State state;
    private Thread thread;

    public double UPS = 60;
    public double FPS = 60;

    @Override
    public void run() {
        //////////////////////  Game loop in here

        int ticks = 0;                                  //for information
        int frames = 0;                                 //for information
        long timer = System.currentTimeMillis();        //for information

        double lastTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;

        while (state == State.RUNNING) {
            long now = System.nanoTime();
            deltaU += (now - lastTime) / timeU;
            deltaF += (now - lastTime) / timeF;
            lastTime = now;

            if (deltaU >= 1) {
                Game.tick();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                Game.render();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {          //print information
                timer += 1000;
                System.out.println(frames + "FPS  |  " + ticks + "Ticks Per Second");
                frames = 0;
                ticks = 0;
            }
        }
        stop();
        //////////////////////
    }

    public synchronized void start() {
        if (state == State.RUNNING) return;
        state = State.RUNNING;                  //change the state of the game loop
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (state != State.RUNNING) return;
        state = State.END;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

