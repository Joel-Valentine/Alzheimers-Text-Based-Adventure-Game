package anonymous.Entities;

import anonymous.Entities.Items.Item;
import anonymous.Player.Player;
import anonymous.Mechanics.GameEngine;

/**
 * Created by anonymous on 11/03/2016.
 */
public class Exits extends Entity{

    private boolean keyRequired;
    private Item key;

    public Exits(String nameOfEntity, String descOfEntity, int roomNo) {
        setRoom(roomNo);
        setNameOfEntity(nameOfEntity);
        setDescOfEntity(descOfEntity);
        setKeyRequired(false);
        setInstructs("\nType 'yes' to go through the " + getNameOfEntity() + "\n" + "Type 'no' to move somewhere else\nOnce you go through you will be placed in the 'c' position of the room.. be careful\n");
    }

    public Exits(String nameOfEntity, String descOfEntity, int roomNo, Item key) {
        setKey(key);
        setKeyRequired(true);
        setRoom(roomNo);
        setNameOfEntity(nameOfEntity);
        setDescOfEntity(descOfEntity);
        setInstructs("\nType 'yes' to go through the " + getNameOfEntity() + "\n" + "Type 'no' to move somewhere else\nThis door requires a " + getKey().getNameOfEntity() + " to open it, if it is in your inventory it will be used automatically\nOnce you go through you will be placed in the 'c' position of the room.. be careful\n");
    }


    public void askingUserForInput(Player p, GameEngine ge){
        p.input();
        if (p.getInput().equals("yes")) {
            if(isKeyRequired()){
                setInstructs("\nType 'yes' to go through the " + getNameOfEntity() + "\n" + "Type 'no' to move somewhere else\nOnce you go through you will be placed in the 'c' position of the room.. be careful\n");
            }
            System.out.println("\nYou step through the " + getNameOfEntity());
            p.energyDepletion();
            System.out.println("Your energy is now " + p.getEnergy() + "\n");
            p.setGlobalLocation(ge.getAllRooms().get(getRoom()));
            p.getGlobalLocation().encounterRoom(ge);
            System.out.println("You go to the centre of the room");
            p.getGlobalLocation().getPointsInRoom().get("c").encountered(p, ge);
            setAnswered(true);
        } else if (p.getInput().equals("no")) {
            leave();
        }
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        System.out.println("\nThis is the " + getNameOfEntity() + "\n" + getDescOfEntity());
        setAnswered(false);
        while(!isAnswered()) {
            if (!isKeyRequired()) {
                System.out.println(getInstructs());
                askingUserForInput(p, ge);
            }
            if (isKeyRequired() && p.getInventory().containsKey(getKey().getNameOfEntity())) {
                System.out.println(getInstructs());
                askingUserForInput(p, ge);
            }
            if (isKeyRequired() && !p.getInventory().containsKey(getKey().getNameOfEntity())) {
                System.out.println("\nYou require a " + getKey().getNameOfEntity() + " to go through this " + getNameOfEntity() + " go find one and then try to go through this door!!\n");
                setAnswered(true);
            }
        }
    }

    public boolean isKeyRequired() {
        return keyRequired;
    }

    public void setKeyRequired(boolean keyRequired) {
        this.keyRequired = keyRequired;
    }

    public Item getKey() {
        return key;
    }

    public void setKey(Item key) {
        this.key = key;
    }
}
