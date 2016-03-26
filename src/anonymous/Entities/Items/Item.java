package anonymous.Entities.Items;

import anonymous.Entities.Entity;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

/**
 * Created by joelvalentine on 15/03/2016.
 */
public class Item extends Entity {

    private int healthRegen;
    private int damage;

    public Item(String name, String descrip, int healthRegen, int damage){
        setDamage(damage);
        setHealthRegen(healthRegen);
        setNameOfEntity(name);
        setDescOfEntity(descrip);
        setInstructs("\nType 'pickup' to pick the " + getNameOfEntity() + " up\nType 'leave' to go somewhere else\n");
    }

    @Override
    public void encountered(Player p, GameEngine ge) {
        setAnswered(false);
        System.out.println("\nYou have found a " + getNameOfEntity() + " " + getDescOfEntity());
        if(getHealthRegen() == 0){
            System.out.println("This " + getNameOfEntity() + " will deal " + getDamage() + " damage");
        }else{
            System.out.println("This " + getNameOfEntity() + " will heal " + getHealthRegen() + " health points");
        }
        System.out.println(getInstructs());
        while (!isAnswered()) {
            p.input();
            if (p.getInput().equals("pickup")) {
                setAnswered(true);
                p.putItemInInventory(getNameOfEntity(), this);
                System.out.println("\nYou decide to pickup the " + getNameOfEntity() + "\n");
                removeEntityFromRoom(p);
            } else if (p.getInput().equals("leave")) {
                setAnswered(true);
                System.out.println("\nYou decide to leave the item and go somewhere else\n");
                p.move(ge);
            } else {
                System.out.println(getInstructs());
            }
        }
    }

    public void interact(Player p) {
        setAnswered(false);
        while(!isAnswered()){
            if (getHealthRegen() == 0) {
                if(p.getCurrentlyEquipped().isEmpty()){
                    equipItem(p);
                }else if(!p.getCurrentlyEquipped().isEmpty()) {
                    alreadyEquipped(p);
                }
            }else {
                eatItem(p);
            }
        }
    }

    public void removeFromEquipped(Player p){
        System.out.println("\nYou have removed the " + p.getCurrentlyEquipped().keySet().toString().replaceAll("[\\[\\],]",""));
        String equippedItem = p.getCurrentlyEquipped().firstKey();
        p.setDamage(p.getStandardDamage());
        p.getCurrentlyEquipped().remove(equippedItem);
        System.out.println("Your damage is now " + p.getDamage() + "\n");
    }

    public void equipItem(Player p){
        System.out.println("\nType 'equip' to equip this " + getNameOfEntity() + "\nType 'leave' to leave the inventory\n");
        p.input();
        if (p.getInput().equals("equip")) {
            p.setDamage(getDamage());
            setAnswered(true);
            p.getCurrentlyEquipped().put(getNameOfEntity(), this);
            System.out.println("your damage is now " + p.getDamage() + "\n");
        }else if(p.getInput().equals("leave")){
            leave();
        }
    }

    public void alreadyEquipped(Player p){
        if(p.getCurrentlyEquipped().firstKey().equals(getNameOfEntity())){
            System.out.println("\nYou are already equipping this " + getNameOfEntity() + " would you like to remove it?\nType 'yes' or 'no'\n");
            p.input();
            if(p.getInput().equals("yes")){
                removeFromEquipped(p);
                setAnswered(true);
            }else if(p.getInput().equals("no")){
                System.out.println("\nYou decide not to remove the " + getNameOfEntity() + "\n");
                setAnswered(true);
            }
        }else{
            System.out.println("\nBefore equipping this " + getNameOfEntity() + " you must first remove the " + p.getCurrentlyEquipped().keySet().toString().replaceAll("[\\[\\],]","") + "\nType 'remove' to remove the " + p.getCurrentlyEquipped().keySet().toString().replaceAll("[\\[\\],]","") + "\n");
            p.input();
            if (p.getInput().equals("remove")) {
                removeFromEquipped(p);
            }
        }
    }

    public void eatItem(Player p){
        System.out.println("\nType 'eat' to eat this " + getNameOfEntity() + "\n");
        p.input();
        if(p.getInput().equals("eat")){
            if(p.getHealth() + getHealthRegen() >= p.getStandardHealth()){
                System.out.println("\nYour current health is " + p.getHealth() + " if you eat this " + getNameOfEntity() + " you will be wasting " + ((p.getHealth() + getHealthRegen()) - p.getStandardHealth()) + " points of health as the max health is 100\nAre you sure you want to eat it?\n'yes' or 'no'\n");
                p.input();
                if(p.getInput().equals("yes")){
                    p.setHealth(p.getStandardHealth());
                    System.out.println("\nYou eat the " + getNameOfEntity() + " your health is now " + p.getHealth() + "\n");
                    p.removeItemFromInventory(getNameOfEntity());
                    setAnswered(true);
                }else if(p.getInput().equals("no")){
                    System.out.println("\nYou decide not to waste the health points for no reason. Good job.\n");
                    setAnswered(true);
                }else{
                    System.out.println("Type 'yes' or 'no'");
                }
            }else{
                p.setHealth(p.getHealth() + getHealthRegen());
                System.out.println("\nYou eat the " + getNameOfEntity() + " your health is now " + p.getHealth() + "\n");
                p.removeItemFromInventory(getNameOfEntity());
                setAnswered(true);
            }
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealthRegen() {
        return healthRegen;
    }

    public void setHealthRegen(int healthRegen) {
        this.healthRegen = healthRegen;
    }

}
