package anonymous.Entities.Items;

import anonymous.Entities.Entity;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

/**
 * Created by joelvalentine on 15/03/2016.
 */
public class Item extends Entity {

    private int healthRegen;

    public Item(String name, String descrip, int healthRegen){
        setHealthRegen(healthRegen);
        setNameOfEntity(name);
        setDescOfEntity(descrip);
        setInstructs("\nType 'pickup' to pick the " + getNameOfEntity() + " up\nType 'leave' to go somewhere else\n");
    }

    @Override
    public void encountered(Player p, GameEngine ge) {
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

    public void healthRegeneration(){

    }

    public int getHealthRegen() {
        return healthRegen;
    }

    public void setHealthRegen(int healthRegen) {
        this.healthRegen = healthRegen;
    }

}
