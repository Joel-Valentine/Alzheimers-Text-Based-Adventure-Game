package anonymous.Entities;

import anonymous.Player.Player;
import anonymous.Mechanics.GameEngine;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by anonymous on 11/03/2016.
 */
public abstract class Entity {

    private int room;
    private String roomName;
    private String nameOfEntity;
    private String descOfEntity;
    private String instructs;
    private boolean answered;
    private String tempLocation;

    public Entity(){
    }

    public Entity(String noe, String doe){
        setDescOfEntity(doe);
        setNameOfEntity(noe);
    }

    public Entity(String noe, String doe, int tr){
        setNameOfEntity(noe);
        setDescOfEntity(doe);
        setRoom(tr);
    }

    public void removeEntityFromRoom(Player p, Entity e){
        p.getGlobalLocation().removeEntity(e.getTempLocation());
    }

    public void randomDrop(GameEngine ge, Player p){
        boolean on = true;
        do{
            int randomNum = (int)(Math.random() * ge.getAllItemsInGame().size() + 0);
            if(ge.getAllItemsInGame().get(randomNum).getHealthRegen() > 0){
                on = false;
                System.out.println("The " + getNameOfEntity() + " has dropped a/an " + ge.getAllItemsInGame().get(randomNum).getNameOfEntity() + " is is now in your inventory\n");
                p.putItemInInventory(ge.getAllItemsInGame().get(randomNum).getNameOfEntity(), ge.getAllItemsInGame().get(randomNum));
            }
        }while(on);
    }

    public void instructions(String i){
        System.out.println(getInstructs().toString());
    }

    public void encountered(Player p, GameEngine ge){
        System.out.println("There is nothing here.\n");
    }

    public void leave(){
        System.out.println("\nYou decide to leave the " + getNameOfEntity() + " and go somewhere else\n");
        setAnswered(true);
    }

    public String getInstructs() {
        return instructs;
    }

    public void setInstructs(String instructs) {
        this.instructs = instructs;
    }

    public void setNameOfEntity(String noe) {
        this.nameOfEntity = noe.toLowerCase();
    }

    public void setDescOfEntity(String doe) {
        this.descOfEntity = doe;
    }

    public String getNameOfEntity() {
        return nameOfEntity;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer tr) {
        this.room = tr;
    }

    public String getDescOfEntity() {
        return descOfEntity;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getTempLocation() {
        return tempLocation;
    }

    public void setTempLocation(String tempLocation) {
        this.tempLocation = tempLocation;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "tr=" + room +
                ", nameOfEntity='" + nameOfEntity + '\'' +
                ", descOfEntity='" + descOfEntity + '\'' +
                ", instructs='" + instructs + '\'' +
                ", answered=" + answered +
                '}';
    }
}
