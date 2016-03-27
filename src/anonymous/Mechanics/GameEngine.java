package anonymous.Mechanics;

import anonymous.Entities.Items.Item;
import anonymous.Room.Room;
import anonymous.Player.Player;

import java.util.HashMap;

/**
 * Created by joelvalentine on 11/03/2016.
 */
public class GameEngine {

    private HashMap<Integer, Room> allRooms = new HashMap<>();
    private HashMap<Integer, Item> allItemsInGame = new HashMap<>();

    public GameEngine(Player player) {
    }

    public void instructions(){
        System.out.println("\nTo move around rooms type any of the directions to a compass (e.g. 'n' 'e' 'swn')\nWhen you encounter an enemy you can type 'leave' to leave, 'attack' to attack the enemy and 'auto attack' to bypass manual attack\nTo access your inventory type 'inventory' once in your inventory you can type the name of the item to interact with it or type 'leave' to leave the inventory\n");
    }

    public void addToRooms(Room tr){
        allRooms.put(allRooms.size(), tr);
    }

    public void addItemToGame(Item item){
        getAllItemsInGame().put(getAllItemsInGame().size(), item);
    }

    public HashMap<Integer, Room> getAllRooms() {
        return allRooms;
    }

    public HashMap<Integer, Item> getAllItemsInGame() {
        return allItemsInGame;
    }

    public void run(Player player) {
        player.setGlobalLocation(allRooms.get(0));
        System.out.println("This is the " + player.getGlobalLocation().getNameOfRoom() + "\n" + player.getGlobalLocation().getContext());
//        player.getGlobalLocation().getPointsInRoom().get("C").encountered(player, this);
        while(!player.isAlive()){
            player.move(this);
        }
    }
}
