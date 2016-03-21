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
        System.out.println(getInstructs());
        while (!isAnswered()) {
            p.input();
            if (p.getInput().equals("pickup")) {
                setAnswered(true);
                p.putItemInInventory(getNameOfEntity(), this);
                removeEntityFromRoom(p);
                System.out.println("You decide to pickup the " + getNameOfEntity() + "\n");
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
                    System.out.println("\nType 'equip' to equip this " + getNameOfEntity() + "\n");
                    p.input();
                        if (p.getInput().equals("equip")) {
                        p.setDamage(getDamage());
                        setAnswered(true);
                        p.getCurrentlyEquipped().put(getNameOfEntity(), this);
                        System.out.println("your damage is now " + p.getDamage() + "\n");
                    }
                }else if(!p.getCurrentlyEquipped().isEmpty()) {
                    System.out.println("\nBefore equipping this " + getNameOfEntity() + " you must first remove the " + p.getCurrentlyEquipped().keySet().toString().replaceAll("[\\[\\],]","") + "\nType 'remove' to remove the " + p.getCurrentlyEquipped().keySet().toString().replaceAll("[\\[\\],]","") + "\n");
                    p.input();
                    if (p.getInput().equals("remove")) {
//                        p.setDamage(p.getDamage() - getDamage());
                        setAnswered(true);
                        System.out.println("You have removed the " + p.getCurrentlyEquipped().keySet().toString().replaceAll("[\\[\\],]",""));
                        String equippedItem = p.getCurrentlyEquipped().firstKey();
                        Entity itm = p.getCurrentlyEquipped().get(p.getCurrentlyEquipped().firstKey());
                        Item item2 = (Item) itm;
                        p.setDamage(p.getDamage() - item2.getDamage());
                        p.getCurrentlyEquipped().remove(p.getCurrentlyEquipped().firstKey());
                        System.out.print(" your damage is now " + p.getDamage() + "\n");
//                        System.out.println("Your damage is now " + p.getDamage() + "\n");
                    }
                }
            }else {
                System.out.println("Type 'eat' to eat this " + getNameOfEntity() + "\n");
                p.input();
                if(p.getInput().equals("eat")){
                    p.setHealth(getHealthRegen());
                    System.out.println("You eat the " + getNameOfEntity() + " your health is now " + p.getHealth() + "\n");
                }
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
