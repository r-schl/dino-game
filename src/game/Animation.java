package game;

import data.Data;
import enums.ObjectState;

import java.awt.image.BufferedImage;

public class Animation {
    BufferedImage[] arr;
    ObjectState state;
    double imagePerTick = 1.0/6.0;
    int currentIndex = 0;


    public Animation(ObjectState objectState, double imagePerTick, String[] arr){
        BufferedImage[] temp = new BufferedImage[arr.length];
        for(int i = 0; i< arr.length; i++){
            temp[i] = Data.loadImg(arr[i]);
        }
        this.arr = temp;
        state = objectState;
        this.imagePerTick = imagePerTick;
    }

    public double getImgPerTick(){
        return imagePerTick;
    }

    public BufferedImage getNext(){
        if(currentIndex == arr.length-1){
            currentIndex = 0;
        }else{
            currentIndex++;
        }
        return arr[currentIndex];
    }

    public BufferedImage get(int index){
        return arr[index];
    }

    public void set(int index, BufferedImage bufferedImage){
        arr[index] = bufferedImage;
    }

    public int size(){
        return arr.length;
    }

    public int getHeight(){
        return arr[0].getHeight();
    }

    public int getWidth(){
        return arr[0].getWidth();
    }

}
