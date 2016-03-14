package jv.Mechanics;

import jv.Entities.Enemies.Enemy;
import jv.Entities.Exits;
import jv.Entities.Friendlies.Henry;
import jv.Player.Player;
import jv.Room.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joelvalentine on 11/03/2016.
 */
public class GameEngine {

    private Map<Integer, Room> allRooms = new HashMap<>();

    public GameEngine(Player player) {

    }

    public void instructions(){
        System.out.println("T \n");
    }

    public void addToRooms(Room tr){
        allRooms.put(allRooms.size(), tr);
    }

    public Map<Integer, Room> getAllRooms() {
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
