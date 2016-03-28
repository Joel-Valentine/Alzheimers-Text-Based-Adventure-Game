package anonymous;

import anonymous.Entities.Enemies.Enemy;
import anonymous.Entities.Exits;
import anonymous.Entities.Friendlies.Friendly;
import anonymous.Entities.Friendlies.Henry;
import anonymous.Entities.Furniture.Furniture;
import anonymous.Entities.Items.Item;
import anonymous.Player.Player;
import anonymous.Mechanics.GameEngine;
import anonymous.Room.Room;

/**
 * Created by anonymous on 16/02/2016.
 */
public class main {

    public static void main(String args[]) {

        //Constants
        Player player = new Player(100, 3, 10);
        GameEngine ge = new GameEngine(player);
        Henry henry = new Henry("Henry", "is a bright pink bunny wearing a tophat and a monocle.");

        //creation of everything else in the game

        //creating enemies
        Enemy troll = new Enemy("Troll", "The troll has a somewhat recognizable face but you can't seem to remember where from.\nHis skin is pale grey and his breath stinks of rotting flesh.\nHe looks aggressive and as if he will attack you", 8, 20, "ME NO LIKE LOOK OF YOU");
        Enemy troll2 = new Enemy("Troll", "The troll has a somewhat recognizable face but you can't seem to remember where from.\nHis skin is pale grey and his breath stinks of rotting flesh.\nHe looks aggressive and as if he will attack you", 8, 20, "ME NO LIKE LOOK OF YOU");
        Enemy potato = new Enemy("Potato", "This is just some random potato BUT YOU MUST KILLS IT", 1, 10, "WHAT ARE YOU DOING HERE HOOMAN");

        //creating furniture
        Furniture draws = new Furniture("chest of draws", "This chest of draws looks like it has a mouth");
        Furniture plop = new Furniture("noicee", "test");

        //creating items to go in rooms/furniture
        Item candleStick = new Item("candlestick", "This candlestick seems to be ble to talk.. it has a strange smile on its... face?", 0, 10, ge);
        Item fork = new Item("fork", "this is an fork", 0, 2, ge);
        Item apple = new Item("apple", "this is a scrumptious apple", 10, 0, ge);
        Item potatop = new Item("potato", "this is a silly little potato", 20, 0, ge);
        Item energyDrink = new Item("energy drink", "this is drink will restore energy",ge ,1);
        Item memory = new Item("Troll memory", "This is a memory fragment from your past", "The troll was once one of your friends from university, after finding this a part of your memory has been restored", ge);

        //creating quest reward items
        Item excalibur = new Item("excalibur", "This sword is very mighty", 0, 100, ge);
        Item sRmap = new Item("Starting room map", "This map will show you everything in the starting room", 0, ge);

        //Creating rooms
        Room sR = new Room("Starting room", "You wake up on a smooth, clean floor. All you can see is a blinding white light.\nThere doesn't seem to be an end to the walls and a start to the ceiling or floor.\nYou see a small silhouette figure in the corner of the room wearing what seems to be a tophat..\n");
        Room bR = new Room("Brain room", "The room has strange flesh looking walls, ceiling and floor. Every time you take a step you hear a squelching noise.\nYou are faced with a huge flesh door to the north, with a sign above it that says 'Brain Entrance'\n");
        Room cR = new Room("celery room", "The room has a huge staircase to the north.");

        //creating friendlies
        Friendly wizzy = new Friendly("Wizard", "This is an ancient wizard trained in the dark arts", "Good evening peasant..", apple, 10, excalibur);

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
        sR.addEntity("n", sRExit1, ge);
        sR.addEntity("c", henry, ge);
        sR.addEntity("e", troll, ge);
        sR.addEntity("w", wizzy, ge);
        sR.addEntity("s", apple, ge);
        sR.addEntity("ne", memory, ge);
        sR.addEntity("se", sRmap, ge);
        sR.addEntity("nw", energyDrink, ge);
        sR.addEntity("sw", excalibur, ge);

        //adding entities to locations in the bR room
        bR.addEntity("s", bRExit1, ge);
        bR.addEntity("sw", troll2, ge);

        //adding items to furniture

        //setting players location globally(the room) and location within that room
        player.setGlobalLocation(ge.getAllRooms().get(0));
        player.getGlobalLocation().encounterRoom();
        player.getGlobalLocation().getPointsInRoom().get("c").encountered(player, ge);

        //running the Game Engine
        ge.run(player);
    }
}
