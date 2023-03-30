package game;

import enums.ObjectState;
import enums.State;
import enums.Type;
import ui.Gui;
import ui.Render;

import java.util.ArrayList;

public class Game {
    public static ObjectList objects = new ObjectList();
    public static Render render;
    public static int speed;
    public static int ticks;
    public static int groundHeight = 40;
    public static State state;
    public static int score;
    public static int startTime;
    public static boolean hitbox = false;

    public static void start() {
        objects.clearAll();
        speed = 6;
        startTime = (int) System.currentTimeMillis();
        ticks = 0;
        score = 0;
        state = State.RUNNING;
        objects.addPlayer();
        objects.addBigCactus(Gui.draw.getWidth());
        objects.addGround(0);
        objects.addGround(1200);
        objects.addGround(2400);
        objects.addCloud(500, 50);
    }

    public static void stop() {
        speed = 0;
        for (Object object : objects.getAll()) {
            object.setdX(0);
            object.setdY(0);
        }
        state = State.END;
    }

    public static void render() {
        if (render == null) {
            render = new Render();
        }
        render.start();
        /////////////////////// Render in here
        render.background();        //white screen
        for (Object object : objects.getAll()) {  //all Objects
            render.object(object);
            if (hitbox) render.hitBox(object);
        }

        if (state == State.END) {
            render.endMenu();
        }

        int now = (int) System.currentTimeMillis();
        if (Game.state == State.RUNNING && now - startTime >= 100) {
            //score = (Math.round((System.currentTimeMillis() - startTime)/1000 * 10.0)/10.0);
            score++;
            startTime = (int) System.currentTimeMillis();
        }
        render.score(score);
        ///////////////////////
        render.end();
    }


    public static void tick() {
        for (int i = 0; i < objects.getAll().size(); i++) {
            Object object = objects.getAll().get(i);
            if ((object.getX() + object.width) <= -Gui.draw.getWidth()) objects.remove(object);
            object.tick();
        }
        if (objects.getLast(objects.clouds).getX() < Gui.draw.getWidth()) {
            objects.addCloud(objects.getLast(objects.clouds).getX() + (int) (Math.random() * 350) + 200, (int) (Math.random() * 90) + 30);
        }
        if (objects.getLast(objects.obstacles).getX() < Gui.draw.getWidth()) {
            randomObstacle();
        }
        if (objects.grounds.get(0).getX() + 1200 <= -Gui.draw.getWidth()) {
            objects.addGround(objects.getLast(objects.grounds).getX() + 1200);
        }
        if (ticks == 300) {
            ticks = 0;
        }
        ticks++;
    }


    public static void randomObstacle() {
/*        System.out.println("llel");
        int type = (int) (Math.random() * 5) + 1;
        int randomX = (int) (Math.random() * 300) + 180;
        if (type >= 1 && type <= 2) {
            objects.addBigCactus(objects.getLast(objects.obstacles).getX() + randomX);
        } else if (type >= 3 && type <= 4) {
            int randomAmount = (int) (Math.random() * 3) + 1;
            for (int i = 0; i < randomAmount; i++) {
                int offset = 0;
                if (i == 0) offset = randomX;
                else offset = 20;
                int randomType = (int) (Math.random() * 4) + 1;
                objects.addSmallCactus(objects.getLast(objects.obstacles).getX() + offset, randomType);
            }
        } else if (type == 5) {
            int ran = (int) (Math.random() * 2) + 1;
            int y;
            if (ran == 1) {
                y = 75;
            } else {
                y = 130;
            }
            objects.addBird(objects.getLast(objects.obstacles).getX() + randomX, Gui.draw.getHeight() - Game.groundHeight - y);
        }*/

        /////////
        int randomType;
        int yOffset;
        boolean cactus = (((int)(Math.random() *4))>0);
        int xGab = (int) (Math.random() * 300) + 180;
        boolean bigCactus = false;

        if(cactus){
            int amount = (int) (Math.random() * 3) + 1; //between 1 and 3
            for (int i = 0; i < amount; i++) {
                int type = (int) (Math.random() * 2) + 1;
                if(bigCactus) type = 2;
                if (i !=0) xGab = 25;
                switch (type) {
                    case 1:
                        objects.addBigCactus(objects.getLast(objects.obstacles).getX() + xGab);
                        bigCactus = true;
                        break;
                    case 2:
                        randomType = (int) (Math.random() * 4) + 1;
                        objects.addSmallCactus(objects.getLast(objects.obstacles).getX() + xGab, randomType);
                        break;
                }
            }
        }else{
            randomType = (int) (Math.random() * 2) + 1;
            if (randomType == 1) yOffset = 75;
            else yOffset = 130;
            objects.addBird(objects.getLast(objects.obstacles).getX() + xGab, Gui.draw.getHeight() - Game.groundHeight - yOffset);
        }





    }

}
