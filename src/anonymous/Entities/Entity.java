package anonymous.Entities;

import anonymous.Player.Player;
import anonymous.Mechanics.GameEngine;

/**
 * Created by joelvalentine on 11/03/2016.
 */
public abstract class Entity {

    private Integer room;
    private String nameOfEntity;
    private String descOfEntity;
    private String instructs;
    private boolean answered;

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

    public void removeEntityFromRoom(Player p){
        for(int i = 0; i<p.getGlobalLocation().getAllPossibleIndexs().length; i++){
            Entity val = p.getGlobalLocation().getPointsInRoom().get(p.getGlobalLocation().getAllPossibleIndexs()[i]);
            if(val.getNameOfEntity() == getNameOfEntity()){
                p.getGlobalLocation().removeEntity(p.getGlobalLocation().getAllPossibleIndexs()[i]);
            }
        }
    }

    public void randomDrop(GameEngine ge, Player p){
        int randomNum = (int)(Math.random() * ge.getAllItemsInGame().size() + 0);
        p.putItemInInventory(ge.getAllItemsInGame().get(randomNum).getNameOfEntity(), ge.getAllItemsInGame().get(randomNum));
        System.out.println("The " + getNameOfEntity() + " has dropped a/an " + ge.getAllItemsInGame().get(randomNum).getNameOfEntity() + " is is now in your inventory\n");
    }

    public void instructions(String i){
        System.out.println(getInstructs().toString());
    }

    public void encountered(Player p, GameEngine ge){
//        System.out.println("\nThis is the: " + getNameOfEntity() + "\nIts description: " + getDescOfEntity());
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
