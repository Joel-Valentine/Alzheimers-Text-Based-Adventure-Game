package jv.Room;

import jv.Entities.Entity;
import jv.Entities.Nothing;

import java.util.HashMap;

/**
 * Created by joelvalentine on 04/03/2016.
 */
public class Room {

    private HashMap<String, Entity> pointsInRoom = new HashMap<>(9);
    private String context;

    public Room(String s) {
        context = s;
        fillingHashmapWithNull();
    }

    public String getContext() {
        return context;
    }

    public void removeEntity(String i){
        pointsInRoom.remove(i);
        pointsInRoom.put(i, new Nothing());
    }

    public void addEntity(String i, Entity e){
        pointsInRoom.put(i,e);
    }

    public void fillingHashmapWithNull(){
        String[] allPossibleIndexs = {"NW", "N", "NE", "W", "C", "E", "SW", "S", "SE"};
        for(int i = 0; i<allPossibleIndexs.length; i++){
            pointsInRoom.put(allPossibleIndexs[i], new Nothing());
        }
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
