package game;           //belongs to package "game"

import enums.ObjectState;
import enums.State;
import enums.Type;
import structure.Loop;
import ui.Gui;

import java.sql.SQLSyntaxErrorException;


public class Entity extends Object {

    boolean shouldduck = false;
    public Entity(Type type, int x, int y, int dX, int dY, Animation[] animations) {
        setX(x);
        setY(y);
        setdY(dY);
        setdX(dX);
        setState(ObjectState.FALLING);
        setType(type);
        this.animations = animations;
        updateSize();
        setHitBoxOffsetX(10);
        setHitBoxOffsetY(10);
    }

    @Override
    public void tick() {
        //collision detection
        for (int i = 0; i <Game.objects.obstacles.size(); i++){
                if(getBounds(true).intersects(Game.objects.obstacles.get(i).getBounds(true))){      //die
                    setState(ObjectState.DIED);
                    Game.stop();
                    setdY(0);
                }
        }
        //update of parent class
        super.updateSize();
        super.move();
        if((int)(Game.ticks % (1/getAnimation(getState()).getImgPerTick())) == 0) super.updateImage();

        //switch states
        switch (getState()){
            case FALLING:
                gravity = gravity + 0.8;
                setdY((int) gravity);
                if(getY() > Gui.draw.getHeight()-Game.groundHeight - getHeight()) {
                    setY(Gui.draw.getHeight() - Game.groundHeight - getHeight() - 1);
                    if(shouldduck) setState(ObjectState.DUCKING);
                    else setState(ObjectState.ONGROUND);
                    shouldduck = false;
                    setdY(0);
                    updateImage();
                }
                break;

            case ONGROUND:
            case DUCKING:
                setdY(0);
                setY(Gui.draw.getHeight()- Game.groundHeight - getHeight() -1);
                break;

            case JUMPING:
                gravity = gravity - 0.8;
                setdY((int) - gravity);
                if (gravity <= 0.0) {
                    setdY(1);
                    setState(ObjectState.FALLING);
                }
                break;
        }
    }

    public void jump(double jumpVel) { //jumpVel is the velocity
        if (getState() == ObjectState.ONGROUND) {
            gravity = jumpVel;
            setState(ObjectState.JUMPING);
        }
    }

    public void duck(boolean activate){
        if(activate){
            if(getState() == ObjectState.ONGROUND){
                setState(ObjectState.DUCKING);
                updateImage();
            }else if(getState() != ObjectState.DUCKING && getState() != ObjectState.DIED){
                setState(ObjectState.FALLING);
                gravity = 17;
                shouldduck = true;
            }
        }
        else{
            if(getState()== ObjectState.DUCKING){
                setState(ObjectState.ONGROUND);
                updateImage();
            }
        }
    }

}
