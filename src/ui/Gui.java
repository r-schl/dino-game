package ui;

import data.Data;
import java.awt.Font;

public class Gui {
    public static int width = 270;
    public static int height = width/4;
    public static int scale = 4;
    public static Window window;
    public static Draw draw;
    public static Font basicFont = Data.getFont(Data.rscPath + "fonts/varsity_regular.ttf");
    public static Font americanFont = Data.getFont(Data.rscPath + "fonts/American Captain.ttf");


    public static void buildWindow(){
        window = new Window("T-Rex");
    }

    public static void buildGame(){
        draw = new Draw(width, height, scale);
        window.add(draw);
        window.pack();
    }
}
