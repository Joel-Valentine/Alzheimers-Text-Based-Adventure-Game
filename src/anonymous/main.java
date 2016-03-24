package anonymous;

import anonymous.Entities.Enemies.Enemy;
import anonymous.Entities.Exits;
import anonymous.Entities.Friendlies.Henry;
import anonymous.Entities.Furniture.Furniture;
import anonymous.Entities.Items.Item;
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
        Enemy troll = new Enemy("Troll", "The troll has a somewhat recognizable face but you can't seem to remember where from.\nHis skin is pale grey and his breath stinks of rotting flesh.\nHe looks aggressive and as if he will attack you", 8, 20, "ME NO LIKE LOOK OF YOU");
        Enemy potato = new Enemy("Potato", "This is just some random potato BUT YOU MUST KILLS IT", 1, 10, "WHAT ARE YOU DOING HERE HOOMAN");

        //creating friendlies
        Henry henry = new Henry("Henry", "is a bright pink bunny wearing a tophat and a monocle.");

        //creating furniture
        Furniture draws = new Furniture("chest of draws", "This chest of draws looks like it has a mouth");
        Furniture plop = new Furniture("noicee", "test");

        //creating items
        Item candleStick = new Item("candlestick", "This candlestick seems to be ble to talk.. it has a strange smile on its... face?", 0, 10);
        Item fork = new Item("fork", "this is an fork", 0, 2);
        Item apple = new Item("apple", "this is a scrumptious apple", 10, 0);

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
//        sR.addEntity("n", sRExit1);
//        sR.addEntity("c", henry);
//        sR.addEntity("e", troll);
//        sR.addEntity("s", potato);
//        sR.addEntity("w", candleStick);
//        sR.addEntity("se", fork);
//        sR.addEntity("ne", apple);
        sR.addEntity("s", draws);
        sR.addEntity("n", plop);

        //adding entities to locations in the bR room
        bR.addEntity("s", bRExit1);
        bR.addEntity("n", bRExit2);

        //adding items to furniture
        draws.putItemsInFurniture(fork);
        draws.putItemsInFurniture(candleStick);

        //running the Game Engine
        ge.run(player);

        //debugging
        //System.out.println(player.getGlobalLocation());
    }
}
