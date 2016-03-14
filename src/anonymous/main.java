package anonymous;

import anonymous.Entities.Enemies.Enemy;
import anonymous.Entities.Exits;
import anonymous.Entities.Friendlies.Henry;
import anonymous.Player.Player;
import anonymous.Mechanics.GameEngine;
import anonymous.Room.Room;

/**
 * Created by joelvalentine on 16/02/2016.
 */
public class main {

    public static void main(String args[]) {

        //Constants
        Player player = new Player();
        GameEngine ge = new GameEngine(player);

        //creation of everything else in the game

        //creating enemies
        Enemy troll = new Enemy("Troll", "The troll has a somewhat recognizable face but you can't seem to remember where from.\nHis skin is pale grey and his breath stinks of rotting flesh.\nHe looks aggressive and as if he will attack you", 8, 20, "ME NO LIKE LOOK OF YOU\n");
        Enemy potato = new Enemy("Potato", "This is just some random potato BUT YOU MUST KILLS IT", 1, 10, "WHAT ARE YOU DOING HERE HOOMAN");

        //creating friendlies
        Henry henry = new Henry("Henry", "is a bright pink bunny wearing a tophat and a monocle.");

        //Creating rooms
        Room sR = new Room("You wake up on a smooth, clean floor. All you can see is a blinding white light.\nThere doesn't seem to be an end to the walls and a start to the ceiling or floor.\nYou see a small silhouette figure in the corner of the room wearing what seems to be a tophat..\n");
        Room bR = new Room("The room has strange flesh looking walls, ceiling and floor. Every time you take a step you hear a squelching noise.\nYou are faced with a huge flesh door to the north, with a sign above it that says 'Brain Entrance'\n");
        Room cR = new Room("The room has a huge staircase to the north.");

        //Exits for sR
        Exits sRExit1 = new Exits("North Exit", "There doesn't seem to be an end to this exit but merely the absence of matter behind it.", 1);

        //Exits for bR
        Exits bRExit1 = new Exits("South Exit", "There doesn't seem to be an end to this exit but merely the absence of matter behind it.", 0);
        Exits bRExit2 = new Exits("North Exit", "The north exit leads up the staircase", 2);

        //Adding rooms to rooms hashmap
        ge.addToRooms(sR);
        ge.addToRooms(bR);
        ge.addToRooms(cR);

        //adding entities to locations in the sR room
        sR.addEntity("N", sRExit1);
        sR.addEntity("C", henry);
        sR.addEntity("E", troll);
        sR.addEntity("S", potato);

        //adding entities to locations in the bR room
        bR.addEntity("S", bRExit1);
        bR.addEntity("N", bRExit2);

        //running the Game Engine
        ge.run(player);

        //debugging
        //System.out.println(player.getGlobalLocation());
    }
}
