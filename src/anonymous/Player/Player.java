package anonymous.Player;

import anonymous.Entities.Enemies.Enemy;
import anonymous.Entities.Entity;
import anonymous.Entities.Items.Item;
import anonymous.Mechanics.GameEngine;
import anonymous.Room.Room;

import java.util.*;

/**
 * Created by anonymous on 01/03/2016.
 */

public class Player{
    private int energy;
    private int health;
    private int questPoints;
    private int standardHealth;
    private int standardDamage;
    private int standardEnergy;
    private int memoriesCollected;
    private String input;
    private Room globalLocation;
    private int damage;
    private Scanner sc = new Scanner(System.in);
    private Map<String, Item> inventory = new TreeMap<>();
    private boolean Answered;
    private TreeMap<String, Entity> currentlyEquipped = new TreeMap<>();

    public Player(int maxHealthPoints, int maxDefaultDamageValue, int maxEnergyPoints) {
        setStandardHealth(maxHealthPoints);
        setStandardDamage(maxDefaultDamageValue);
        setStandardEnergy(maxEnergyPoints);
        setQuestPoints(0);
        setDamage(getStandardDamage());
        setHealth(getStandardHealth());
        setEnergy(getStandardEnergy());
    }

    public boolean isAlive(GameEngine ge) {
        for(int i = 0; i<getHealth(); i++) {
            if (getHealth() <= 0) {
                System.out.println("\nyou ran out of health and died!\n");
                return true;
            }else if(getEnergy() <= 0){
                System.out.println("\nyou ran out of energy and died!\n");
                return true;
            } else {
                return false;
            }
        }
        return true;
    }


    public boolean hasWon(GameEngine ge){
        if(getMemoriesCollected() == ge.getAllMemoriesInGame().size()){
            System.out.println("You have won the game as you collected all your lost memories from your brain\nIf you would like to continue playing type 'yes' if not type 'no'\n");
            input();
            if(getInput().equals("yes")){
                System.out.println("\nYou decide to continue playing until you get bored.. a wise move as this game is incredible\n");
                setMemoriesCollected(getMemoriesCollected() - 1);
                return false;
            }else if(getInput().equals("no")){
                System.out.println("\nYou decide enough is enough I have battled hard for this win, thanks for playing!");
                return true;
            }
        }else{
            return false;
        }
        return false;
    }

    public void currentStatus(GameEngine ge){
         System.out.println("\nequipped item: " + getCurrentlyEquipped().keySet().toString().replaceAll("\\[\\]", "nothing") + "\ndamage: " + getDamage() + " \nhealth points: " + getHealth() + " \nenergy points: " + getEnergy() + "\nquest points: " + getQuestPoints() + " \nmemories collected: " + getMemoriesCollected() + "/" + ge.getAllMemoriesInGame().size() + "\nitems collected: " + getInventory().size() + "\n");
    }

    public String input(){
        System.out.println("What are you going to do?");
        setInput(sc.nextLine().toLowerCase());
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
            System.out.println();
            getGlobalLocation().encounterRoom(ge);
        }else if(getInput().equals("get player status")){
            System.out.print("\nHere are your current states: ");
            currentStatus(ge);
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
                itm = new Item(key, itm.getDescOfEntity(), itm.getHealthRegen(), itm.getDamage());
            }
        }
        if(itm.getNameOfEntity().contains("memory")){
            setMemoriesCollected(getMemoriesCollected() + 1);
//            System.out.println("you just collected a memory you now have " + getMemoriesCollected() + " memories");
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
        while(!isAnswered()){
            System.out.println("\nType the name of the item as you see it to interact with it\nType 'leave' to stop looking in your inventory\n");
            input();
            if(getInput().equals("leave")){
                System.out.println("\nYou decide to stop looking in your inventory\n");
                setAnswered(true);
            }else if(getInventory().containsKey(getInput())){
                Entity entityItem = getInventory().get(getInput());
                Item item = (Item) entityItem;
                item.interact(this, ge);
                setAnswered(true);
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

    public int getMemoriesCollected() {
        return memoriesCollected;
    }

    public void setMemoriesCollected(int memoriesCollected) {
        this.memoriesCollected = memoriesCollected;
    }

    @Override
    public String toString() {
        return "Player{" +
                "energy=" + energy +
                ", health=" + health +
                ", questPoints=" + questPoints +
                ", memoriesCollected=" + memoriesCollected +
                ", globalLocation=" + globalLocation +
                ", damage=" + damage +
                ", inventory=" + inventory +
                ", currentlyEquipped=" + currentlyEquipped +
                '}';
    }
}
