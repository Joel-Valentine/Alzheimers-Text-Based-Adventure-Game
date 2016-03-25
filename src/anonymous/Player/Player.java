package anonymous.Player;

import anonymous.Entities.Enemies.Enemy;
import anonymous.Entities.Entity;
import anonymous.Entities.Items.Item;
import anonymous.Mechanics.GameEngine;
import anonymous.Room.Room;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Created by joelvalentine on 01/03/2016.
 */

public class Player{
    private int energy;
    private int health;
    private int standardHealth;
    private int standardDamage;
    private int standardEnergy;
    private String input;
    private Room globalLocation;
    private int damage;
    private Scanner sc = new Scanner(System.in);
    private HashMap<String, Entity> inventory = new HashMap<>();
    private boolean Answered;
    private TreeMap<String, Entity> currentlyEquipped = new TreeMap<>();

    public Player() {
        setStandardHealth(100);
        setStandardDamage(3);
        setStandardEnergy(10);
        setDamage(getStandardDamage());
        setHealth(getStandardHealth());
        setEnergy(getStandardEnergy());
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
            System.out.println("\nThat isn't an allowed command try again. Try typing 'instructions' to get available commands\n");
        }
    }

    public void attack(Enemy e){
        e.takeDamage(getDamage(), this);
    }

    public void takeDamage(int damage){
        int result = getHealth() - damage;
        setHealth(result);
    }

    public void putItemInInventory(String key, Item itm){
        getInventory().put(key, itm);
    }

    public void removeItemFromInventory(String key){
        getInventory().remove(key);
    }

    public void viewInventoryItems(){
        setAnswered(false);
        if(getInventory().isEmpty()){
            System.out.println("\nYour inventory is empty! You return to whatever you were doing\n");
            setAnswered(true);
        }else{
            System.out.println("\nYour inventory items are:");
            System.out.println(getInventory().keySet().toString().replaceAll("[\\[\\]]",""));
            System.out.println();
        }
        while(!isAnswered()){
            System.out.println("Type 'leave' to stop looking in your inventory\nType the name of the item as you see it to interact with it\n");
            input();
            if(getInput().equals("leave")){
                System.out.println("\nYou decide to stop looking in your inventory\n");
                setAnswered(true);
            }else if(getInventory().containsKey(getInput())){
                Entity entityItem = getInventory().get(getInput());
                Item item = (Item) entityItem;
                item.interact(this);
                setAnswered(true);
            }else{
                System.out.println("\nType 'leave' to stop looking in your inventory\nType name of item AS YOU SEE IT to interact with item\n");
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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void energyDepletion(){
        setEnergy(getEnergy() - 1);
    }

    public boolean isAnswered() {
        return Answered;
    }

    public void setAnswered(boolean answered) {
        Answered = answered;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public TreeMap<String, Entity> getCurrentlyEquipped() {
        return currentlyEquipped;
    }

    public int getStandardHealth() {
        return standardHealth;
    }

    public void setStandardHealth(int standardHealth) {
        this.standardHealth = standardHealth;
    }

    public int getStandardDamage() {
        return standardDamage;
    }

    public void setStandardDamage(int standardDamage) {
        this.standardDamage = standardDamage;
    }

    public int getStandardEnergy() {
        return standardEnergy;
    }

    public void setStandardEnergy(int standardEnergy) {
        this.standardEnergy = standardEnergy;
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
