package anonymous.Entities;

import anonymous.Player.Player;
import anonymous.Mechanics.GameEngine;

/**
 * Created by joelvalentine on 11/03/2016.
 */
public abstract class Entity {

    private Integer tr;
    private String nameOfEntity;
    private String descOfEntity;
    private String instructs;
    private String question;
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
        setTr(tr);
    }

    public void removeEntityFromRoom(Player p){
        for(int i = 0; i<p.getGlobalLocation().getAllPossibleIndexs().length; i++){
            Entity val = p.getGlobalLocation().getPointsInRoom().get(p.getGlobalLocation().getAllPossibleIndexs()[i]);
            if(val.getNameOfEntity() == getNameOfEntity()){
                p.getGlobalLocation().removeEntity(p.getGlobalLocation().getAllPossibleIndexs()[i]);
            }
        }
    }

    public void instructions(String i){
        System.out.println(getInstructs().toString());
    }

    public void encountered(Player p, GameEngine ge){
//        System.out.println("\nThis is the: " + getNameOfEntity() + "\nIts description: " + getDescOfEntity());
        System.out.println("There is nothing here.\n");
    }

    public String getInstructs() {
        return instructs;
    }

    public void setInstructs(String instructs) {
        this.instructs = instructs;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setNameOfEntity(String noe) {
        this.nameOfEntity = noe;
    }

    public void setDescOfEntity(String doe) {
        this.descOfEntity = doe;
    }

    public String getNameOfEntity() {
        return nameOfEntity;
    }

    public Integer getTr() {
        return tr;
    }

    public void setTr(Integer tr) {
        this.tr = tr;
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
                "tr=" + tr +
                ", nameOfEntity='" + nameOfEntity + '\'' +
                ", descOfEntity='" + descOfEntity + '\'' +
                ", instructs='" + instructs + '\'' +
                ", answered=" + answered +
                '}';
    }
}
