package anonymous.Entities.Furniture;

import anonymous.Entities.Entity;
import anonymous.Entities.Items.Item;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

import java.util.HashMap;

/**
 * Created by anonymous on 22/03/2016.
 */
public class Furniture extends Entity{

    private HashMap<String, Entity> itemsInFurniture = new HashMap<>();

    public Furniture(String nameOfEntity, String descOfEntity){
        setNameOfEntity(nameOfEntity);
        setDescOfEntity(descOfEntity);
        setInstructs("\nType 'search' to search this " + getNameOfEntity() + " for items\nType 'leave' to leave and go somewhere else\n");
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        System.out.println("\nYou see a " + getNameOfEntity() + " " + getDescOfEntity());
        System.out.println(getInstructs());
        setAnswered(false);
        while(!isAnswered()){
            p.input();
            if(p.getInput().equals("search")){
                System.out.println("\nYou decide to search the " + getNameOfEntity() + " this may take some time...\n");
                if(checkIfEmpty()){
                    setAnswered(true);
                }else{
                    searchItemsInFurniture();
                    pickupAnitem(p);
                }
            }else if(p.getInput().equals("leave")){
                leave();
            }else{
                System.out.println(getInstructs());
            }
        }
    }
    
    private boolean checkIfEmpty() {
        if(getItemsInFurniture().isEmpty()){
            try {
                Thread.sleep(2000);
                System.out.println("There are no items in this " + getNameOfEntity() + " you decide to leave and move somewhere else\n");
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            return false;
        }
        return false;
    }
    
    private void pickupAnitem(Player p) {
        System.out.println("type the item you wish to pickup or type 'leave' when you have finished picking up items/want to go somewhere else\n");
        while(!isAnswered()){
            p.input();
            if(getItemsInFurniture().containsKey(p.getInput())){
                p.putItemInInventory(getItemsInFurniture().get(p.getInput()).getNameOfEntity(), (Item) getItemsInFurniture().get(p.getInput()));
                removeItemFromFurniture(p);
                if(getItemsInFurniture().isEmpty()){
                    checkIfPickedUpEveryItem(p);
                }else{
                    System.out.println("\nYou pickup a " + p.getInput() + " and it has been put in your inventory\nThe items left are " + getItemsInFurniture().keySet().toString().replaceAll("[\\[\\]]","") + "\n");
                }
            }else if(p.getInput().equals("leave")){
                System.out.println("\nYou decide to leave and go somewhere else\n");
                setAnswered(true);
            }else{
                System.out.println("type the item you wish to pickup or type 'leave' when you have finished picking up items/want to go somewhere else\n");
            }
        }
    }

    public void removeItemFromFurniture(Player p){
        getItemsInFurniture().remove(p.getInput());
    }

    public void putItemsInFurniture(Entity item){
        getItemsInFurniture().put(item.getNameOfEntity(), item);
    }

    public void checkIfPickedUpEveryItem(Player p){
        if(getItemsInFurniture().isEmpty()){
            System.out.println("\nYou pickup a " + p.getInput() + " there are no more items left in the " + getNameOfEntity() + " you can now go somewhere else\n");
            setAnswered(true);
        }
    }

    public void searchItemsInFurniture(){
        //randomizing the wait when searching for items, could improve this by multiplying it by how many items in the furniture there are.. but this is fine for now
            int randomNum = (int)(Math.random() * 7000 + 3000);
            try {
                Thread.sleep(randomNum);
                System.out.println("You have found these items : ");
                System.out.println(getItemsInFurniture().keySet().toString().replaceAll("[\\[\\]]",""));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

    public HashMap<String, Entity> getItemsInFurniture() {
        return itemsInFurniture;
    }

}
