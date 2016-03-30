package anonymous.Entities.Enemies;

import anonymous.Entities.Entity;
import anonymous.Entities.Items.Item;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

/**
 * Created by anonymous on 13/03/2016.
 */
public class Enemy extends Entity {

    private int health;
    private int initialHealth;
    private int damage;
    private String encounterText;
    private Item rewardItem;

    public Enemy(String s, String s1, int dam, int health, String encounterText){
        setNameOfEntity(s);
        setDescOfEntity(s1);
        setDamage(dam);
        setHealth(health);
        setInitialHealth(health);
        setEncounterText(encounterText);
        setInstructs("\nType 'attack' to attack the " + getNameOfEntity() + "\n" + "Type 'leave' to move somewhere else\nType 'auto attack' if you feel confident enough that you will kill the " + getNameOfEntity() + " without dying\n");
    }

    public Enemy(String s, String s1, int dam, int health, String encounterText, Item reward){
        setRewardItem(reward);
        setNameOfEntity(s);
        setDescOfEntity(s1);
        setDamage(dam);
        setHealth(health);
        setInitialHealth(health);
        setEncounterText(encounterText);
        setInstructs("\nType 'attack' to attack the " + getNameOfEntity() + "\n" + "Type 'leave' to move somewhere else\nType 'auto attack' if you feel confident enough that you will kill the " + getNameOfEntity() + " without dying\n");
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        setAnswered(false);
        System.out.println("\nYou encounter a " + getNameOfEntity() + "!\n" + getDescOfEntity() + "\nThe " + getNameOfEntity() + " will deal " + getDamage() + " damage and has "  + getHealth() +" health\nyou currently have " + p.getHealth() + " health and deal " + p.getDamage() +  " damage\n\n" + getNameOfEntity() + ": " + getEncounterText() + "\n" + getInstructs());
        while (isAlive() && !isAnswered()) {
            p.input();
            if (p.getInput().equals("attack")) {
                p.attack(this);
                System.out.println("\nYou hit the " + getNameOfEntity() + " for " + p.getDamage() + " his health is now " + getHealth());
                attack(p);
                System.out.println("The " + getNameOfEntity() + " hit you for " + getDamage() + " your health is now " + p.getHealth() + "\n");
            }else if(p.getInput().equals("leave")){
                leave();
            }else if(p.getInput().equals("auto attack")) {
                System.out.println();
                while (isAlive()) {
                    p.attack(this);
                    attack(p);
                }
            }else if(p.getInput().equals("inventory")){
                p.viewInventoryItems(ge);
                setAnswered(false);
            }else{
                System.out.println(getInstructs());
            }
            if (!isAlive()) {
                System.out.println("You have killed the " + getNameOfEntity() + " your health is now " + p.getHealth() + " you can now move elsewhere");
                removeEntityFromRoom(p, this);
                if(getRewardItem() == null){
                    randomDrop(ge, p);
                }else if(getRewardItem() != null){
                    p.putItemInInventory(getRewardItem().getNameOfEntity(), getRewardItem());
                    System.out.println("The " + getNameOfEntity() + " has dropped a/an " + getRewardItem().getNameOfEntity() + " is is now in your inventory\n");
                }else{
                    System.out.println();
                }
            }
        }
    }

    public boolean isAlive(){
        if(getHealth() <= 0){
            return false;
        }
        return true;
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

    public Item getRewardItem() {
        return rewardItem;
    }

    public void setRewardItem(Item rewardItem) {
        this.rewardItem = rewardItem;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }
}
