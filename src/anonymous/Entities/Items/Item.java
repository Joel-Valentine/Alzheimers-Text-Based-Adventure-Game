package anonymous.Entities.Items;

import anonymous.Entities.Entity;
import anonymous.Entities.Nothing;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

/**
 * Created by anonymous on 15/03/2016.
 */
public class Item extends Entity {

    private int healthRegen;
    private int damage;
    private String memoryText;
    private int energyRegen;

    public Item(String name, String descrip, int room){
        setRoom(room);
        setNameOfEntity(name);
        setDescOfEntity(descrip);
        setInstructs("\nType 'pickup' to pick the " + getNameOfEntity() + " up\nType 'leave' to go somewhere else\n");
    }

    public Item(String name, String descrip, String memory, GameEngine ge){
        setNameOfEntity(name);
        setDescOfEntity(descrip);
        setMemoryText(memory);
        setInstructs("\nType 'pickup' to pick the " + getNameOfEntity() + " up\nType 'leave' to go somewhere else\n");
        ge.addMemoriesToGame(this);
    }

    public Item(String name, String descrip, int room, GameEngine ge){
        setNameOfEntity(name);
        setDescOfEntity(descrip);
        setRoom(room);
        setInstructs("\nType 'pickup' to pick the " + getNameOfEntity() + " up\nType 'leave' to go somewhere else\n");
        ge.addItemToGame(this);
    }

    public Item(String name, String descrip, GameEngine ge, int energyRegen){
        setEnergyRegen(energyRegen);
        setNameOfEntity(name);
        setDescOfEntity(descrip);
        setInstructs("\nType 'pickup' to pick the " + getNameOfEntity() + " up\nType 'leave' to go somewhere else\n");
        ge.addItemToGame(this);
    }

    public Item(String name, String descrip, int healthRegen, int damage, GameEngine ge){
        setDamage(damage);
        setHealthRegen(healthRegen);
        setNameOfEntity(name);
        setDescOfEntity(descrip);
        setInstructs("\nType 'pickup' to pick the " + getNameOfEntity() + " up\nType 'leave' to go somewhere else\n");
        ge.addItemToGame(this);
    }

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
        if(getEnergyRegen() > 0){
            System.out.println("This " + getNameOfEntity() + " will regenerate" + getEnergyRegen() + " energy");
        }
        if(getDamage() > 0){
            System.out.println("This " + getNameOfEntity() + " will deal " + getDamage() + " damage");
        }
        if(getHealthRegen() > 0){
            System.out.println("This " + getNameOfEntity() + " will heal " + getHealthRegen() + " health points and will give you " + getEnergyRegen() + " energy points");
        }
        System.out.println(getInstructs());
        while (!isAnswered()) {
            p.input();
            if (p.getInput().equals("pickup")) {
                setAnswered(true);
                if(getNameOfEntity().contains("memory")){
                    p.putItemInInventory(getNameOfEntity(), this);
                    System.out.println("\nYou decide to pickup the " + getNameOfEntity() + "\n");
                    removeEntityFromRoom(p, this);
                    p.setMemoriesCollected(p.getMemoriesCollected() + 1);
                }else{
                    p.putItemInInventory(getNameOfEntity(), this);
                    System.out.println("\nYou decide to pickup the " + getNameOfEntity() + "\n");
                    removeEntityFromRoom(p, this);
                }
            } else if (p.getInput().equals("leave")) {
                setAnswered(true);
                System.out.println("\nYou decide to leave the item and go somewhere else\n");
                p.move(ge);
            } else {
                System.out.println(getInstructs());
            }
        }
    }

    private void memoryItem(){
        System.out.println("\nThe memory is as follows:\n" + getMemoryText() + "\nsince this is only information you can't interact and return back to whatever you were doing\n");
        setAnswered(true);
    }

    private void keyItem(GameEngine ge){
        System.out.println("\n" + getDescOfEntity() + " in the " + ge.getAllRooms().get(getRoom()).getNameOfRoom() + "\nsince this is only information you can't interact and return back to whatever you were doing\n");
        setAnswered(true);
    }

    private void mapItem(GameEngine ge) {
        System.out.println();
            for(int i = 0; i<ge.getAllRooms().get(getRoom()).getPointsInRoom().size(); i++){
                if(ge.getAllRooms().get(getRoom()).getPointsInRoom().get(ge.getAllRooms().get(getRoom()).getAllPossibleIndexs()[i]).getNameOfEntity() != null){
                    System.out.println("to the " + ge.getAllRooms().get(getRoom()).getAllPossibleIndexs()[i] + " there is a/an " + ge.getAllRooms().get(getRoom()).getPointsInRoom().get(ge.getAllRooms().get(getRoom()).getAllPossibleIndexs()[i]).getNameOfEntity());
                }
            }
        System.out.println("\nsince this is only information you can't interact and return back to whatever you were doing\n");
        setAnswered(true);
    }

    public void interact(Player p, GameEngine ge) {
        setAnswered(false);
        while(!isAnswered()) {
            if(getEnergyRegen() > 0){
                regenEnergy(p);
            }else if(getHealthRegen() == 0 && getDamage() == 0 && getEnergyRegen() == 0){
                if(getNameOfEntity().contains("map")){
                    mapItem(ge);
                }else if(getNameOfEntity().contains("memory")){
                    memoryItem();
                }else if(getNameOfEntity().contains("key")){
                    keyItem(ge);
                }
            }else if (getHealthRegen() == 0) {
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

    public void regenEnergy(Player p){
        System.out.println("\nthis " + getNameOfEntity() + " will regenerate " + getEnergyRegen() + " energy points\nType 'eat' to eat this " + getNameOfEntity() + "\n");
        p.input();
        if(p.getInput().equals("eat")){
            if(p.getEnergy() + getEnergyRegen() > p.getStandardEnergy()){
                System.out.println("\nYour current energy is " + p.getEnergy() + " if you eat this " + getNameOfEntity() + " you will be wasting " + ((p.getEnergy() + getEnergyRegen()) - p.getStandardEnergy()) + " points of energy as the max energy is " + p.getStandardEnergy() +"\nAre you sure you want to eat it?\n'yes' or 'no'\n");
                p.input();
                if(p.getInput().equals("yes")){
                    p.setEnergy(p.getStandardEnergy());
                    System.out.println("\nYou eat the " + getNameOfEntity() + " your energy is now " + p.getEnergy() + "\n");
                    p.removeItemFromInventory(getNameOfEntity());
                    setAnswered(true);
                }else if(p.getInput().equals("no")){
                    System.out.println("\nYou decide not to waste the energy points for no reason. Good job.\n");
                    setAnswered(true);
                }else{
                    System.out.println("Type 'yes' or 'no'");
                }
            }else{
                p.setEnergy(p.getEnergy() + getEnergyRegen());
                System.out.println("\nYou eat the " + getNameOfEntity() + " your energy is now " + p.getEnergy() + "\n");
                p.removeItemFromInventory(getNameOfEntity());
                setAnswered(true);
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
        System.out.println("\nthis " + getNameOfEntity() + " will deal " + getDamage() + " damage\nType 'equip' to equip this " + getNameOfEntity() + "\nType 'leave' to leave the inventory\n");
        p.input();
        if (p.getInput().equals("equip")) {
            p.setDamage(getDamage());
            setAnswered(true);
            p.getCurrentlyEquipped().put(getNameOfEntity(), this);
            System.out.println("\nYou equip the " + getNameOfEntity() + " your damage is now " + p.getDamage() + "\n");
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
        System.out.println("\nThis " + getNameOfEntity() + " will heal " + getHealthRegen() + " health points\nType 'eat' to eat this " + getNameOfEntity() + "\n");
        System.out.println();
        p.input();
        if(p.getInput().equals("eat")){
            if(p.getHealth() + getHealthRegen() > p.getStandardHealth()){
                System.out.println("\nYour current health is " + p.getHealth() + " if you eat this " + getNameOfEntity() + " you will be wasting " + ((p.getHealth() + getHealthRegen()) - p.getStandardHealth()) + " points of health as the max health is " + p.getStandardHealth() +"\nAre you sure you want to eat it?\n'yes' or 'no'\n");
                p.input();
                if(p.getInput().equals("yes")){
                    //TODO: make energy regenerate
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

    public String getMemoryText() {
        return memoryText;
    }

    public void setMemoryText(String memoryText) {
        this.memoryText = memoryText;
    }

    public int getEnergyRegen() {
        return energyRegen;
    }

    public void setEnergyRegen(int energyRegen) {
        this.energyRegen = energyRegen;
    }
}
