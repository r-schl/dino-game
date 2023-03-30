package ui;

import data.Data;
import io.KeyHandler;

import javax.swing.*;

public class Window extends JFrame {

    public Window(String title){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Gui.width * Gui.scale,Gui.height * Gui.scale);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        addKeyListener(new KeyHandler());
        requestFocus();
        setIconImage(new ImageIcon(Data.loadImg(Data.rscPath+"icon/icon.png")).getImage());
    }


}
