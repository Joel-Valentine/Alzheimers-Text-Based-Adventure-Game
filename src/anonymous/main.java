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

        //creating furniture
//        Furniture draws = new Furniture("chest of draws", "This chest of draws looks like it has a mouth");
//        Furniture plop = new Furniture("noicee", "test");

        //creating items to go in rooms/furniture these will be included in random drops
//        Item candleStick = new Item("candlestick", "This candlestick seems to be ble to talk.. it has a strange smile on its... face?", 0, 10, ge);
        Item apple = new Item("apple", "this is a scrumptious apple", 10, 0, ge);
//        Item energyDrink = new Item("energy drink", "this is drink will restore energy",ge ,1);

        //creating memories this wont be included in random drops
        Item memory = new Item("Troll memory", "This is a memory fragment from your past", "The troll was once one of your friends from university, after finding this a part of your memory has been restored", ge);
        Item joshMemory = new Item("Josh memory", "This is a memory fragment from your past", "Josh used to go to my old school he was one of my best friends but was still really annoying.. I liked that guy\nI should get back in touch", ge);
        Item diggerMemory = new Item("digger memory", "This is a memory fragment from your past", "Wow, i used to work in this thing 9 till 5 everyday for 8 years...", ge);

        //creating questreward items these wont be included in random drops
        Item excalibur = new Item("excalibur", "This sword is very mighty", 0, 100);
//        Item sRmap = new Item("Starting room map", "This map will show you everything in the starting room", 0);

        //creating keys these wont be included in random drops
//        Item sRExit1Key = new Item("key", "this key opens the north exit", 0);

        //Creating rooms
        Room startingRoom = new Room("Starting room", "You wake up on a smooth, clean floor. All you can see is a blinding white light.\nThere doesn't seem to be an end to the walls and a start to the ceiling or floor.\nYou see a small silhouette figure in the corner of the room wearing what seems to be a tophat..\n");
        Room brainRoomEntrance = new Room("Brain room", "The room has strange flesh looking walls, ceiling and floor. Every time you take a step you hear a squelching noise.\nYou are faced with a huge flesh door to the north, with a sign above it that says 'Brain Entrance'\n");
        Room brainStemRoom = new Room("Brain stem room", "This room has thick columns strutting from the ceiling to the floor.. The floor is made of what looks like hard bone..\n");
        Room brainDatabase = new Room("Brain database room", "This room seems to have lots of servers racks containing broken servers...\nIts as if the database is corrupted and missing parts all over\n");
        Room cerebralCortexRoom = new Room("Cerebral cortex room", "This room is grey all over.. a nice change from the flesh walls and floor..\nA strange feeling comes over me.. am i conscious?.. get it? because this room is responsible for consciousness.. get it? I am here all day\n");
        Room brainDiningRoom = new Room("Brain dining room", "This is a dining room.. within a brain? Why is there a dining room inside a brain..\n");
        Room brainKitchenRoom = new Room("Brain kitchen room", "This is a kitchen.. in a brain.. i really don't get why this exists inside here?\n");
        Room homunculusRoom = new Room("homunculus room", "Some say brains are run by small people controlling us.. There should be a small man somewhere in this room\n");
        Room brainLibraryRoom = new Room("Brain library room", "This libraries books have all been torn up and thrown all over the floor.. its hard to concentrate in here\n");

        //Exits for sR
//        Exits sRExit1 = new Exits("North Exit", "There doesn't seem to be an end to this exit but merely the absence of matter behind it.", 1, sRExit1Key);

        //Exits for bR
//        Exits bRExit1 = new Exits("South Exit", "There doesn't seem to be an end to this exit but merely the absence of matter behind it.", 0);
//        Exits bRExit2 = new Exits("North Exit", "The north exit leads up the staircase", 2);

        //creating enemies
        Enemy troll = new Enemy("Troll", "The troll has a somewhat recognizable face but you can't seem to remember where from.\nHis skin is pale grey and his breath stinks of rotting flesh.\nHe looks aggressive and as if he will attack you", 8, 20, "ME NO LIKE LOOK OF YOU");
        Enemy josh = new Enemy("Josh", "This is josh.. we used to go to school together.. i think... He was annoying..\n He doesn't look happy with me.. and looks like he will attack me", 3, 20, "Hey man remember me?");
        Enemy digger = new Enemy("digger", "This digger looks familiar.. i feel like i might have worked with diggers but its all so hazy", 7, 30, "MWAHAHA YOU ALWAYS HATED ME", diggerMemory);
//        Enemy troll2 = new Enemy("Troll", "The troll has a somewhat recognizable face but you can't seem to remember where from.\nHis skin is pale grey and his breath stinks of rotting flesh.\nHe looks aggressive and as if he will attack you", 8, 20, "ME NO LIKE LOOK OF YOU");
//        Enemy potato = new Enemy("Potato", "This is just some random potato BUT YOU MUST KILLS IT", 1, 10, "WHAT ARE YOU DOING HERE HOOMAN");

        //creating friendlies
//        Friendly wizzy = new Friendly("Wizard", "This is an ancient wizard trained in the dark arts", "Good evening peasant..", apple, 10, excalibur);

        //Adding rooms to rooms hashmap
        ge.addToRooms(startingRoom);
        ge.addToRooms(brainRoomEntrance);
        ge.addToRooms(brainStemRoom);
        ge.addToRooms(brainDatabase);
        ge.addToRooms(cerebralCortexRoom);
        ge.addToRooms(brainDiningRoom);
        ge.addToRooms(brainKitchenRoom);
        ge.addToRooms(homunculusRoom);
        ge.addToRooms(brainLibraryRoom);

        //adding entities to locations in the starting room
        startingRoom.addEntity("c", henry, ge);
        startingRoom.addEntity("e", josh, ge);
        startingRoom.addEntity("w", troll, ge);

        //adding entities to locations in the brain entrance room

        //adding entities to locations in the brain entrance room

        //adding entities to locations in the brain entrance room

        //adding entities to locations in the brain entrance room

        //adding entities to locations in the brain entrance room

        //adding items to furniture

        //setting players location globally(the room) and location within that room
        player.setGlobalLocation(ge.getAllRooms().get(0));
        player.getGlobalLocation().encounterRoom();
//        player.getGlobalLocation().getPointsInRoom().get("c").encountered(player, ge);

        //running the Game Engine
        ge.run(player);
    }
}
