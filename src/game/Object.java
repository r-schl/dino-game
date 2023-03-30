package game;

import enums.Dir;
import enums.ObjectState;
import enums.Type;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Object {
    int x;
    int y;
    int dX;
    int dY;
    int width;
    int height;
    int hitBoxOffsetX;
    int HitBoxOffsetY;
    public double gravity;
    ObjectState state;
    Type type;
    Animation[] animations;
    BufferedImage currentImg;

    public int getHitBoxOffsetX() {
        return hitBoxOffsetX;
    }

    public void setHitBoxOffsetX(int hitBoxOffsetX) {
        this.hitBoxOffsetX = hitBoxOffsetX;
    }

    public int getHitBoxOffsetY() {
        return HitBoxOffsetY;
    }

    public void setHitBoxOffsetY(int hitBoxOffsetY) {
        HitBoxOffsetY = hitBoxOffsetY;
    }

    public BufferedImage getCurrentImg() {
        return currentImg;
    }

    public void setCurrentImg(BufferedImage currentImg) {
        this.currentImg = currentImg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public ObjectState getState() {
        return state;
    }

    public void setState(ObjectState state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void tick();


    public void updateSize() {
        setWidth(getAnimation(state).getWidth());
        setHeight(getAnimation(state).getHeight());
    }

    public void updateImage() {
        Animation animation = getAnimation(getState());
        if (getAnimation(getState()).state == animation.state) {
            currentImg = animation.getNext();
        }
    }

    public void move() {
        setY(getY() + getdY());         //move delta y to the bottom
        setX(getX() + getdX());         //move delta x to the right
    }

    public Animation getAnimation(ObjectState state) {
        for (Animation animation : animations) {
            if (animation.state == state) {
                return animation;
            }
        }
        return getAnimation(ObjectState.DEFAULT);
    }

    public Rectangle getBounds(boolean offset) {
        if (offset) return new Rectangle(x + getHitBoxOffsetX(), y + getHitBoxOffsetY(), width - (getHitBoxOffsetX() * 2), height - (getHitBoxOffsetY() * 2));
        else  return new Rectangle(x, y, width, height);
    }
/*
    public Rectangle getSideBounds(Dir direction){
        switch (direction){
            case TOP:
                return new Rectangle(x+10, y, width -20, 5);
            case BOTTOM:
                return new Rectangle(x+10, y + height -5, width-20, 5);
            case LEFT:
                return new Rectangle(x, y+10, 5, height-20);
            case RIGHT:
                return new Rectangle(x + width -5, y + 10, 5, height-20);
        }
        return null;
    }*/
}
