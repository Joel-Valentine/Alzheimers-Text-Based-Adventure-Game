package anonymous.Player;

import anonymous.Entities.Enemies.Enemy;
import anonymous.Entities.Entity;
import anonymous.Entities.Items.Item;
import anonymous.Mechanics.GameEngine;
import anonymous.Room.Room;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by joelvalentine on 01/03/2016.
 */

public class Player{
    private int energy = 10;
    private int health = 100;
    private String input;
    private Room globalLocation;
    private int damage = 3;
    Scanner sc = new Scanner(System.in);
    private HashMap<String, Entity> inventory = new HashMap<>();


    public Player() {
    }

    public boolean isAlive() {
        for(int i = 0; i<getHealth(); i++) {
            if (getHealth() < 0 || getEnergy() < 0) {
                return true;
            }else {
                return false;
            }
        }
        return true;
    }

    public String input(){
        System.out.println("What are you going to do?");
        setInput(sc.nextLine());
        return getInput();
    }

    public void move(GameEngine ge){
        input();
        if(getGlobalLocation().getPointsInRoom().get(getInput()) != null) {
            getGlobalLocation().getPointsInRoom().get(getInput()).encountered(this, ge);
        }else if(getInput().equals("instructions")){
            ge.instructions();
        }else if(getInput().equals("inventory")){
            viewInventoryItems();
        }else{
            System.out.println("that isn't an allowed command try again. Try typing 'instructions' to get available commands\n");
        }
    }

    public void attack(Enemy e){
        e.takeDamage(getDamage(), this);
    }

    public void takeDamage(int damage){
        int result = getHealth() - damage;
        setHealth(result);
    }

    public void putItemInInventory(String key, Entity itm){
        getInventory().put(key, itm);
    }

    public void viewInventoryItems(){
        if(getInventory().isEmpty()){
            System.out.println("\nYour inventory is empty!\n");
        }else{
            System.out.println("\nYour inventory items are:");
            for(int i = 0; i<getInventory().size(); i++){
                System.out.println(getInventory().keySet());
                if(i == (getInventory().size() - 1)){
                    System.out.println("");
                }
            }
        }
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setGlobalLocation(Room globalLocation) {
        this.globalLocation = globalLocation;
    }

    public Room getGlobalLocation() {
        return globalLocation;
    }

    public HashMap<String, Entity> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Entity> inventory) {
        this.inventory = inventory;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getDamage() {
        return damage;
    }

    public void energyDepletion(){
        int depletion = getEnergy();
        energy = depletion - 1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Player{" +
                "energy=" + energy +
                ", health=" + health +
                ", input='" + input + '\'' +
                ", globalLocation=" + globalLocation +
                ", damage=" + damage +
                ", sc=" + sc +
                ", inventory=" + inventory +
                '}';
    }
}
