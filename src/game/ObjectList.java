package game;

import data.Data;
import enums.ObjectState;
import enums.Type;
import ui.Gui;

import java.util.ArrayList;

public class ObjectList{
    public Entity player;
    ArrayList<Object> entities = new ArrayList<>();
    ArrayList<Object> obstacles = new ArrayList<>();
    ArrayList<Object> grounds = new ArrayList<>();
    ArrayList<Object> clouds = new ArrayList<>();
    ArrayList[] arr = { entities, obstacles, grounds, clouds};

    ObjectList(){

    }
    ArrayList<Object> getAll(){
        ArrayList<Object> allarr = new ArrayList<>();
        allarr.addAll(grounds);
        allarr.addAll(clouds);
        allarr.addAll(obstacles);
        allarr.addAll(entities);
        allarr.add(player);
        return allarr;
    }

    void clearAll(){
        for(ArrayList i : arr){
            i.clear();
        }
    }

    void getInfo(){
        System.out.println("Grounds: " + grounds.size() + " Clouds: " + clouds.size() + "  Obstacles:  " + obstacles.size() );
    }

    Object getLast(ArrayList<Object> list){
        return list.get(list.size()-1);
    }

    void remove(Object object){
        grounds.remove(object);
        clouds.remove(object);
        obstacles.remove(object);
        entities.remove(object);
    }

    void addPlayer(){
        player = new Entity(Type.PLAYER, 100,300, 0,0, new Animation[]{
                new Animation(ObjectState.DEFAULT, 1, new String[]{
                        Data.rscPath+ "tRex/1.png"
                }),
                new Animation(ObjectState.ONGROUND, 1.0/4, new String[]{
                        Data.rscPath+ "tRex/1.png",
                        Data.rscPath+ "tRex/2.png",
                        Data.rscPath+ "tRex/3.png"
                }),
                new Animation(ObjectState.DUCKING, 1.0/5.5, new String[]{
                        Data.rscPath+"tRex/duck1.png",
                        Data.rscPath+"tRex/duck2.png"
                }),
                new Animation(ObjectState.DIED, 1, new String[]{
                        Data.rscPath+ "tRex/died.png"
                })
        }
        );
    }

    void addSmallCactus(int x, int type){
        obstacles.add(new Thing(Type.SMALLCACTUS,  x, Gui.draw.getHeight()- Game.groundHeight - 40, - Game.speed,0, new Animation[]{
                new Animation(ObjectState.DEFAULT, 1, new String[]{
                        Data.rscPath+ "smallCactus/" +type+".png"
                })
        }));
    }

    void addBigCactus(int x){
        obstacles.add(new Thing(Type.BIGCACTUS, x, Gui.draw.getHeight()- Game.groundHeight - 50, - Game.speed,0, new Animation[]{
                new Animation(ObjectState.DEFAULT, 1, new String[]{
                        Data.rscPath+ "bigCactus/1.png"
                })
        }));
    }

    void addBird(int x, int y){
        System.out.println("Bird added");
        obstacles.add(new Thing(Type.BIRD, x, y, -Game.speed, 0, new Animation[]{
                new Animation(ObjectState.DEFAULT, 1.0/10, new String[]{
                        Data.rscPath+ "bird/bird1.png",
                        Data.rscPath+ "bird/bird2.png"
                })
        }));
    }

    void addCloud(int x, int y){
        clouds.add(new Thing(Type.GROUND, x, y, - Game.speed/3,0, new Animation[]{
                new Animation(ObjectState.DEFAULT, 1, new String[]{
                        Data.rscPath+ "background/cloud.png"
                })
        }));
    }

    void addGround(int x){
        grounds.add(new Thing(Type.GROUND, x, Gui.draw.getHeight()- 55, -Game.speed,0, new Animation[]{
                new Animation(ObjectState.DEFAULT, 1, new String[]{
                        Data.rscPath+ "background/ground.png"
                })
        }));
    }
}
