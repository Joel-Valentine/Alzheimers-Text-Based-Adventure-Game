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
        System.out.println("To move around rooms type any of the directions to a compass (e.g. 'N' 'E' 'SW')\nWhen you encounter an enemy you can type 'leave' to leave, 'attack' to attack the enemy and 'auto attack' to bypass manual attack\n");
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
