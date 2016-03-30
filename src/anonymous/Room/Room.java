package anonymous.Room;

import anonymous.Entities.Entity;
import anonymous.Entities.Items.Item;
import anonymous.Entities.Nothing;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

import java.util.HashMap;

/**
 * Created by anonymous on 04/03/2016.
 */
public class Room {

    private HashMap<String, Entity> pointsInRoom = new HashMap<>();
    private String context;
    private String nameOfRoom;
    private String easyMode;
    private int roomNo;
    private String[] allPossibleIndexs = {"nw", "n", "ne", "w", "c", "e", "sw", "s", "se"};


    public Room(String roomName, String roomContext) {
        setNameOfRoom(roomName);
        setContext(roomContext);
        fillingHashmapWithNull();
    }

    public void encounterRoom(GameEngine ge){
        if(ge.getDifficultySetting().equals("easy")){
            easySetting();
            System.out.println("This is the " + getNameOfRoom() + "\n" + getContext() + getEasyMode());
        }else {
            System.out.println("This is the " + getNameOfRoom() + "\n" + getContext());
        }
    }

    public String getContext() {
        return context;
    }

    public void removeEntity(String i){
        getPointsInRoom().put(i, new Nothing());
    }

    public void addEntity(String i, Entity e, GameEngine ge){
        getPointsInRoom().put(i,e);
        e.setTempLocation(i);
        e.setRoomName(ge.getAllRooms().get(getRoomNo()).getNameOfRoom());
    }

    public void fillingHashmapWithNull(){
        for(int i = 0; i<getAllPossibleIndexs().length; i++){
            pointsInRoom.put(getAllPossibleIndexs()[i], new Nothing());
        }
    }

    public String[] getAllPossibleIndexs() {
        return allPossibleIndexs;
    }

    public HashMap<String, Entity> getPointsInRoom() {
        return pointsInRoom;
    }
    
    public void setContext(String context) {
        this.context = context;
    }

    public void easySetting(){
        for(int i = 0; i < getPointsInRoom().size(); i++){
            if(getPointsInRoom().get(getAllPossibleIndexs()[i]).getNameOfEntity() != null){
                if(getEasyMode() == null){
                    setEasyMode("\nto the " + getAllPossibleIndexs()[i] + " there is a/an " + getPointsInRoom().get(getAllPossibleIndexs()[i]).getNameOfEntity() + "\n");
                }else{
                    setEasyMode(getEasyMode() + "to the " + getAllPossibleIndexs()[i] + " there is a/an " + getPointsInRoom().get(getAllPossibleIndexs()[i]).getNameOfEntity() + "\n");
                }
            }
        }
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public void setNameOfRoom(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom.toLowerCase();
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getEasyMode() {
        return easyMode;
    }

    public void setEasyMode(String easyMode) {
        this.easyMode = easyMode;
    }

    @Override
    public String toString() {
        return "Room{" +
                "pointsInRoom=" + pointsInRoom +
                ", context='" + context + '\'' +
                '}';
    }

}
