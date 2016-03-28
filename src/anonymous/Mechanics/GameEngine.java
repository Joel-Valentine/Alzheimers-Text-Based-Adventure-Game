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
    private HashMap<Integer, Item> allMemoriesInGame = new HashMap<>();

    public GameEngine(Player player) {
    }

    public void instructions(){
        System.out.println("\nTo win the game collect all " + getAllMemoriesInGame().size() + " of the memories\nTo move around inside of a room type any of the following " + getAllRooms().get(0).getPointsInRoom().keySet() + " 'c' being the centre\nWhen you encounter an exit you will have the choice to go through to another room with 'yes' pr 'no', this is how you move between rooms\nTo access your inventory type 'inventory'\nTo get your current room information type 'get current room'\nTo get your current player stats type 'get player status'\nAll other instructions will be told to you when you encounter something, now go play the game!\n");
    }

    public void addToRooms(Room tr){
        getAllRooms().put(getAllRooms().size(), tr);
        tr.setRoomNo(getAllRooms().size()-1);
    }

    public void addItemToGame(Item item){
        getAllItemsInGame().put(getAllItemsInGame().size(), item);
    }

    public void addMemoriesToGame(Item item){
        getAllMemoriesInGame().put(getAllMemoriesInGame().size(), item);
    }

    public HashMap<Integer, Room> getAllRooms() {
        return allRooms;
    }

    public HashMap<Integer, Item> getAllItemsInGame() {
        return allItemsInGame;
    }

    public HashMap<Integer, Item> getAllMemoriesInGame() {
        return allMemoriesInGame;
    }

    public void run(Player player) {
        System.out.println("\nPlease play game with terminal/cmd window maximised/as large as it goes\n");
        player.setGlobalLocation(allRooms.get(0));
        System.out.println("This is the " + player.getGlobalLocation().getNameOfRoom() + "\n" + player.getGlobalLocation().getContext());
        player.getGlobalLocation().getPointsInRoom().get("c").encountered(player, this);
        while(!player.isAlive() && !player.hasWon(this)){
            player.move(this);
        }
    }
}
