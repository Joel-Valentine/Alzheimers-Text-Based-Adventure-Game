package anonymous.Player;

import anonymous.Entities.Enemies.Enemy;
import anonymous.Entities.Entity;
import anonymous.Entities.Items.Item;
import anonymous.Mechanics.GameEngine;
import anonymous.Room.Room;

import java.util.*;

/**
 * Created by joelvalentine on 01/03/2016.
 */
////TODO: implement scoring system when game ends
    //// TODO: implement story
public class Player{
    private int energy;
    private int health;
    private int questPoints;
    private int standardHealth;
    private int standardDamage;
    private int standardEnergy;
    private String input;
    private Room globalLocation;
    private int damage;
    private Scanner sc = new Scanner(System.in);
    Map<String, Item> inventory = new TreeMap<>();
    private boolean Answered;
    private TreeMap<String, Entity> currentlyEquipped = new TreeMap<>();

    public Player() {
        setStandardHealth(100);
        setStandardDamage(3);
        setStandardEnergy(8);
        setQuestPoints(0);
        setDamage(getStandardDamage());
        setHealth(getStandardHealth());
        setEnergy(getStandardEnergy());
    }

    public boolean isAlive() {
        for(int i = 0; i<getHealth(); i++) {
            if (getHealth() <= 0 || getEnergy() <= 0) {
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
            viewInventoryItems(ge);
        }else if(getInput().equals("get current room")){
            System.out.println("\nYou are currently in the " +  getGlobalLocation().getNameOfRoom() + "\n" + getGlobalLocation().getContext());
        }else if(getInput().equals("get player status")){
            System.out.println("\nHere are you current states:\nequipped item: " + getCurrentlyEquipped().firstKey() + "\ndamage: " + getDamage() + " \nhealth points: " + getHealth() + " \nenergy points: " + getEnergy() + "\nquest points: " + getQuestPoints() + " \n");
        }else{
            System.out.println("\nThat isn't an allowed command try again. Try typing 'instructions' to get available commands\n");
        }
    }

    public void attack(Enemy enemy){
        enemy.takeDamage(getDamage(), this);
    }

    public void takeDamage(int damage){
        int result = getHealth() - damage;
        setHealth(result);
    }

    public void putItemInInventory(String key, Item itm){
        String initKey = key;
        for(int i = 1; i<getInventory().size()+1; i++){
            if(getInventory().containsKey(key) || getInventory().containsKey(key + i)){
                key = initKey + i;
            }
        }
        getInventory().put(key, itm);
    }

    public void removeItemFromInventory(String key){
        getInventory().remove(key);
    }

    public void viewInventoryItems(GameEngine ge){
        setAnswered(false);
        if(getInventory().isEmpty()){
            System.out.println("\nYour inventory is empty! You return to whatever you were doing\n");
            setAnswered(true);
        }else{
            System.out.println("\nYour inventory items are:");
            System.out.println(getInventory().keySet().toString().replaceAll("[\\[\\]]",""));
        }
        System.out.println("\nType the name of the item as you see it to interact with it\nType 'leave' to stop looking in your inventory\n");
        while(!isAnswered()){
            input();
            if(getInput().equals("leave")){
                System.out.println("\nYou decide to stop looking in your inventory\n");
                setAnswered(true);
            }else if(getInventory().containsKey(getInput())){
                Entity entityItem = getInventory().get(getInput());
                Item item = (Item) entityItem;
                item.interact(this, ge);
                setAnswered(true);
            }else{
                System.out.println("\nType the name of the item as you see it to interact with it\nType 'leave' to stop looking in your inventory\n");
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

    public Map<String, Item> getInventory() {
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

    public int getQuestPoints() {
        return questPoints;
    }

    public void setQuestPoints(int questPoints) {
        this.questPoints = questPoints;
    }

    @Override
    public String toString() {
        return "Player{" +
                "energy=" + energy +
                ", health=" + health +
                ", questPoints=" + questPoints +
                ", standardHealth=" + standardHealth +
                ", standardDamage=" + standardDamage +
                ", standardEnergy=" + standardEnergy +
                ", input='" + input + '\'' +
                ", globalLocation=" + globalLocation +
                ", damage=" + damage +
                ", sc=" + sc +
                ", inventory=" + inventory +
                ", Answered=" + Answered +
                ", currentlyEquipped=" + currentlyEquipped +
                '}';
    }
}
