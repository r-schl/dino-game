package ui;

import game.Object;
import java.awt.*;
import java.awt.image.BufferStrategy;


import static ui.Gui.draw;

public class Render{
    Graphics graphics;
    BufferStrategy bufferStrategy;

    public Render(){
        draw.createBufferStrategy(3);
        bufferStrategy = draw.getBufferStrategy();
    }


    public void start(){
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void background(){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, draw.getWidth(), draw.getHeight());
    }

    public void object(Object ob){
        graphics.drawImage(ob.getCurrentImg(), ob.getX(),ob.getY(), null);
    }

    public void hitBox(Object ob){
        graphics.setColor(Color.RED);
        Rectangle hB = ob.getBounds(true);
        graphics.drawRect((int)hB.getX(),(int) hB.getY(), (int)hB.getWidth(), (int)hB.getHeight());
    }

    public void endMenu(){
        int heigth = draw.getHeight();
        int width = draw.getWidth();
        int alpha = 127; // 50% transparent
        Color myColour = new Color(0, 0, 0, alpha);
        graphics.setColor(myColour);
        graphics.setColor(Color.GRAY);
        graphics.setFont(Gui.basicFont.deriveFont(35f));
      //  graphics.fillRect(0, draw.getHeight()/2-(heigth/2), width, heigth);
        graphics.drawString("GAME OVER", draw.getWidth()/2 -100, 120);
        graphics.setFont(Gui.basicFont.deriveFont(22f));
        graphics.drawString("PRESS ENTER TO RESTART", draw.getWidth()/2 -144, 145);
        graphics.fillRect(draw.getWidth()/2 -144, 154, 288, 2);
    }

    public void score(int score){
        graphics.setColor(Color.GRAY);
        graphics.setFont(Gui.americanFont.deriveFont(23f));
        String result = "";
        for(int i = 0; i<(5-String.valueOf(score).length()); i++){
            result = result + "0";
        }
        result = result+ Integer.toString(score);
        graphics.drawString(result, draw.getWidth()-80, 40);
    }

    public void end(){
        graphics.dispose();
        bufferStrategy.show();
    }
}
