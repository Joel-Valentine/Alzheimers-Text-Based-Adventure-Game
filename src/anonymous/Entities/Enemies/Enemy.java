package anonymous.Entities.Enemies;

import anonymous.Entities.Entity;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

/**
 * Created by joelvalentine on 13/03/2016.
 */
public class Enemy extends Entity {

    private int health;
    private int damage;
    private String encounterText;

    public Enemy(String s, String s1, int dam, int health, String encounterText){
        setNameOfEntity(s);
        setDescOfEntity(s1);
        setDamage(dam);
        setHealth(health);
        setEncounterText(encounterText);
        setInstructs("Type 'attack' to attack the " + getNameOfEntity() + "\n" + "Type 'leave' to move somewhere else\nType 'auto attack' if you feel confident enough that you will kill the " + getNameOfEntity() + " without dying\n");
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        setAnswered(false);
        System.out.println("\nYou encounter a " + getNameOfEntity() + "!\n" + getDescOfEntity() + "\nThe " + getNameOfEntity() + " will deal " + getDamage() + " damage and has "  + getHealth() +" health\n\n" + getNameOfEntity() + ": " +getEncounterText() + "\n" + getInstructs());
        while (isAlive() && !isAnswered()) {
            p.input();
            if (p.getInput().equals("attack")) {
                p.attack(this);
                System.out.println("\nYou hit the " + getNameOfEntity() + " for " + p.getDamage() + " his health is now " + getHealth());
                attack(p);
                System.out.println("The " + getNameOfEntity() + " hit you for " + getDamage() + " your health is now " + p.getHealth() + "\n");
            }else if(p.getInput().equals("leave")){
                setAnswered(true);
                System.out.println("You decide to not attack the " + getNameOfEntity() + " and move somewhere else\n");
                p.move(ge);
            }else if(p.getInput().equals("auto attack")){
                System.out.println("");
                while(isAlive()){
                    p.attack(this);
                    attack(p);
                }
            }else{
                System.out.println(getInstructs());
            }
            if (!isAlive()) {
                System.out.println("You have killed the " + getNameOfEntity() + " your health is now " + p.getHealth() + "\n");
                p.getGlobalLocation().getPointsInRoom().containsValue(this);
            }
        }
    }

    public boolean isAlive(){
        if(getHealth() < 0){
            return false;
        }else{
            return true;
        }
    }

    public void removeEnemy(){

    }

    public void attack(Player p){
        p.takeDamage(getDamage());
    }

    public void takeDamage(int result, Player p){
        result = getHealth() - p.getDamage();
        setHealth(result);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getEncounterText() {
        return encounterText;
    }

    public void setEncounterText(String encounterText) {
        this.encounterText = encounterText;
    }
}
