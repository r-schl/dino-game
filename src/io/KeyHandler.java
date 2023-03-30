package io;

import enums.ObjectState;
import enums.State;
import game.Game;
import structure.Loop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (Loop.state == State.RUNNING){
            switch (key){
                case KeyEvent.VK_SPACE:
                    Game.objects.player.jump(12.0);
                    break;
                case KeyEvent.VK_DOWN:
                    Game.objects.player.duck(true);
                    break;
                case KeyEvent.VK_ENTER:
                    if(Game.state == State.END){
                        Game.start();
                    }
                break;
                case KeyEvent.VK_H:
                    if(!Game.hitbox) Game.hitbox = true;
                    else Game.hitbox = false;
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (Loop.state == State.RUNNING){
            switch (key){
                case KeyEvent.VK_DOWN:
                    Game.objects.player.duck(false);
                    break;
            }
        }
    }
}
