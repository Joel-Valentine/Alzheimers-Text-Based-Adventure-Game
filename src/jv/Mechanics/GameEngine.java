package jv.Mechanics;

import jv.Entities.Enemies.Troll;
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
        //creation of everything else in the game

        //creating enemies
        Troll troll = new Troll("Troll", "The troll has a somewhat recognizable face but you can't seem to remember where from\nHis skin is a pale grey and his breath Stinks of rotting flesh");

        //creating friendlies
        Henry henry = new Henry("Henry", "is a bright pink bunny wearing a tophat and a monocle.");

        //Creating rooms
        Room sR = new Room("You wake up on a smooth, clean floor. All you can see is a blinding white light.\nThere doesn't seem to be an end to the walls and a start to the ceiling or floor.\nYou see a small silhouette figure in the corner of the room wearing what seems to be a tophat..\n");
        Room bR = new Room("The room has strange flesh looking walls, ceiling and floor. Every time you take a step you can hear a squelch noise.\nYou are faced with a huge flesh door to the north with a sign above it that says 'Brain Entrance'\n");
        Room cR = new Room("The room has a huge staircase to the north.");

        //Exits for sR
        Exits sRExit1 = new Exits("North Exit", "There doesn't seem to be an end to this exit but merely the absence of matter behind it.", 1);

        //Exits for bR
        Exits bRExit1 = new Exits("South Exit", "There doesn't seem to be an end to this exit but merely the absence of matter behind it.", 0);
        Exits bRExit2 = new Exits("South Exit", "There doesn't seem to be an end to this exit but merely the absence of matter behind it.", 2);

        //Adding rooms to rooms hashmap
        addToRooms(sR);
        addToRooms(bR);
        addToRooms(cR);

        //adding entities to locations in the sR room
        sR.addEntity("N", sRExit1);
        sR.addEntity("C", henry);
        sR.addEntity("E", troll);

        //adding entities to locations in the bR room
        bR.addEntity("S", bRExit1);
        bR.addEntity("N", bRExit2);

    }

    public void instructions(){
        System.out.println("T");
    }

    public void addToRooms(Room tr){
        allRooms.put(allRooms.size(), tr);
    }

    public Map<Integer, Room> getAllRooms() {
        return allRooms;
    }

    public void run(Player player) {
        player.setGlobalLocation(allRooms.get(0));
        System.out.println(player.getGlobalLocation().getContext().toString());
//        player.getGlobalLocation().getPointsInRoom().get("C").encountered(player, this);
        while(!player.isAlive()){
            player.move(this);
        }
    }
}
