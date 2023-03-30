package ui;


import java.awt.*;
import java.awt.image.BufferStrategy;


public class Draw extends Canvas {
    Dimension size;
    BufferStrategy bufferStrategy;

    public Draw(int width, int height, int scale){
        size = new Dimension(width * scale, height*scale);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setFocusable(false);
    }

    public int getWidth(){
        return (int) size.getWidth();
    }

    public int getHeight(){
        return (int) size.getHeight();
    }

}
