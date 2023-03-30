package game;

import enums.ObjectState;
import enums.Type;


public class Thing extends Object {

    public Thing(Type type, int x, int y, int dX , int dY, Animation[] animations){
        setX(x);
        setY(y);
        setdY(dY);
        setdX(dX);
        setState(ObjectState.DEFAULT);
        setType(type);
        this.animations = animations;
        updateSize();
        setHitBoxOffsetX(0);
        setHitBoxOffsetY(0);
    }

    @Override
    public void tick(){
        //update of parent class
        super.updateSize();
        super.move();
        if((int)(Game.ticks % (1/getAnimation(getState()).getImgPerTick())) == 0) super.updateImage();
    }
}

