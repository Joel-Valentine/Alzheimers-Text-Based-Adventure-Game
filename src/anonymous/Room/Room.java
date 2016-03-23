package anonymous.Room;

import anonymous.Entities.Entity;
import anonymous.Entities.Nothing;

import java.util.HashMap;

/**
 * Created by joelvalentine on 04/03/2016.
 */
public class Room {

    private HashMap<String, Entity> pointsInRoom = new HashMap<>();
    private String context;
    private String[] allPossibleIndexs = {"nw", "n", "ne", "w", "c", "e", "sw", "s", "se"};


    public Room(String s) {
        context = s;
        fillingHashmapWithNull();
    }

    public String getContext() {
        return context;
    }

    public void removeEntity(String i){
        pointsInRoom.put(i, new Nothing());
    }

    public void addEntity(String i, Entity e){
        pointsInRoom.put(i,e);
    }

    public void fillingHashmapWithNull(){
        for(int i = 0; i<getAllPossibleIndexs().length; i++){
            pointsInRoom.put(getAllPossibleIndexs()[i], new Nothing());
        }
    }

    public void setAllPossibleIndexs(String[] allPossibleIndexs) {
        this.allPossibleIndexs = allPossibleIndexs;
    }

    public String[] getAllPossibleIndexs() {
        return allPossibleIndexs;
    }

    public HashMap<String, Entity> getPointsInRoom() {
        return pointsInRoom;
    }

    public void setPointsInRoom(String s, Entity e) {
        pointsInRoom.put(s, e);
    }

    @Override
    public String toString() {
        return "Room{" +
                "pointsInRoom=" + pointsInRoom +
                ", context='" + context + '\'' +
                '}';
    }

}
