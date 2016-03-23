package anonymous.Mechanics;

import anonymous.Room.Room;
import anonymous.Player.Player;

import java.util.HashMap;

/**
 * Created by joelvalentine on 11/03/2016.
 */
public class GameEngine {

    private HashMap<Integer, Room> allRooms = new HashMap<>();

    public GameEngine(Player player) {
    }

    public void instructions(){
        System.out.println("\nTo move around rooms type any of the directions to a compass (e.g. 'n' 'e' 'swn')\nWhen you encounter an enemy you can type 'leave' to leave, 'attack' to attack the enemy and 'auto attack' to bypass manual attack\nTo access your inventory type 'inventory' once in your inventory you can type the name of the item to interact with it or type 'leave' to leave the inventory\n");
    }

    public void addToRooms(Room tr){
        allRooms.put(allRooms.size(), tr);
    }

    public HashMap<Integer, Room> getAllRooms() {
        return allRooms;
    }

    public void run(Player player) {
        player.setGlobalLocation(allRooms.get(0));
        System.out.println(player.getGlobalLocation().getContext());
//        player.getGlobalLocation().getPointsInRoom().get("C").encountered(player, this);
        while(!player.isAlive()){
            player.move(this);
        }
    }
}
