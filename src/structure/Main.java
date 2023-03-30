package structure;


import data.Data;
import enums.ObjectState;
import enums.Type;
import game.Animation;
import game.Entity;
import game.Game;
import game.Thing;
import ui.Gui;
import ui.Render;

import java.awt.image.BufferedImage;


public class Main {

    public static void main(String[] args) {
        Gui.buildWindow();
        Gui.buildGame();
        Game.start();
        Loop gameLoop = new Loop();
        gameLoop.start();
    }
}
