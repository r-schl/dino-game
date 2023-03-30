package data;

import ui.Gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.awt.Font;

public class Data {
    public static ArrayList<myImage> loadedImages = new ArrayList<>();
    public static String rscPath ="./res/";

    public static BufferedImage loadImg(String path){
        BufferedImage loadedImg = null;
        for(myImage image : loadedImages){
            if(image.path.equals(path)){
                loadedImg = image.img;
            }
        }

        if(loadedImg == null){
            BufferedImage img = null;
            try {
                if(path != null){
                    InputStream in = Data.class.getClassLoader().getResourceAsStream(path);
                    if(in != null){
                        img = ImageIO.read(in);
                    }else{
                        System.out.println(path + " konnte nicht geladen werden");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            loadedImages.add(new myImage(img, path));
            return img;
        }else{
            return loadedImg;
        }
    }

    public static BufferedImage resizeImg(BufferedImage img, int width, int height){
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }


    public static Font getFont(String path){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, Gui.class.getClassLoader().getResourceAsStream(path));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(path + " konnte nicht gelesen werden!");
        return null;
    }

}


class myImage{
    BufferedImage img;
    String path;

    myImage (BufferedImage img, String path){
        this.img = img;
        this.path = path;
    }
}
