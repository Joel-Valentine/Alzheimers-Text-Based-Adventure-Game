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
        Player player = new Player(100, 3, 2);
        GameEngine ge = new GameEngine();
        Henry henry = new Henry("Henry", "is a bright pink bunny wearing a tophat and a monocle.");

        //creation of everything else in the game

        //creating furniture
        Furniture draws = new Furniture("chest of draws", "This chest of draws looks like it has a mouth");
        Furniture cupboard = new Furniture("cupboard", "This cupboard smells of rotting flesh");
        Furniture desk = new Furniture("a desk", "This desk is made from glass and is the only clean thing in this place");
        Furniture chair = new Furniture("a miniature chair", "This chair is very small.. I guess the people who use this are also small?");

        //creating items to go in rooms/furniture these will be included in random drops
        Item candleStick = new Item("candlestick", "This candlestick seems to be ble to talk.. it has a strange smile on its... face?", 0, 5, ge);
        Item knife = new Item("Knife", "this knife ", 0, 8, ge);
        Item bat = new Item("baseball bat", "This candlestick seems to be ble to talk.. it has a strange smile on its... face?", 0, 7, ge);
        Item apple = new Item("apple", "this is a scrumptious apple", 10, 0, ge);
        Item turnip = new Item("turnip", "like an orange but white", 3, 0, ge);
        Item rottingApple = new Item("rotting apple", "This apple is smelly and decomposing", 1, 0, ge);
        Item bannana = new Item("banana", "a fresh banana", 6, 0, ge);
        Item carrot = new Item("carrot", "a orange, crunchy vegetable", 8, 0, ge);
        Item cake = new Item("slice of cake", "a sweet slice of cake", 11, 0, ge);
        Item energyBar = new Item("energy bar", "this energy bar will restore energy",ge ,1);

        //creating memories this wont be included in random drops
        Item wifeMemory = new Item("wife memory", "this is a memory fragment from your past", "I remember now that i have a wife", ge);
        Item childrenMemory = new Item("children memory", "this is a memory fragment from your past", "I remember that i have two children, a boy named Charlie and a girl named Stephanie", ge);
        Item peanutMemory = new Item("peanut memory", "this is a memory fragment from your past", "I used to keep a peanut in my pocket for good luck.. peanuts are small..\nI found this in a small chair. it all makes sense ", ge);
        Item joshMemory = new Item("Josh memory", "This is a memory fragment from your past", "Josh used to go to my old school he was one of my best friends but was still really annoying.. I liked that guy\nI should get back in touch", ge);
        Item diggerMemory = new Item("digger memory", "This is a memory fragment from your past", "Wow, i used to work in this thing 9 till 5 everyday for 8 years...", ge);

        //creating questreward items these wont be included in random drops
        Item excalibur = new Item("excalibur", "This sword is very mighty", 0, 100);
//        Item sRmap = new Item("Starting room map", "This map will show you everything in the starting room", 0);

        //creating keys these wont be included in random drops
        Item brainStemRoomKeyEW = new Item("pink key", "this key opens the east exit", 3);
//        Item sRExit1Key = new Item("key", "this key opens the north exit", 0);

        //Creating rooms
        Room startingRoom = new Room("Starting room", "You wake up on a smooth, clean floor. All you can see is a blinding white light.\nThere doesn't seem to be an end to the walls and a start to the ceiling or floor.\nYou see a small silhouette figure in the corner of the room wearing what seems to be a tophat..\n");
        Room brainEntranceRoom = new Room("Brain entrance room", "The room has strange flesh looking walls, ceiling and floor. Every time you take a step you hear a squelching noise.\nYou are faced with a huge flesh door to the north, with a sign above it that says 'Brain Entrance'\n");
        Room brainStemRoom = new Room("Brain stem room", "This room has thick columns strutting from the ceiling to the floor.. The floor is made of what looks like hard bone..\n");
        Room brainDatabase = new Room("Brain database room", "This room seems to have lots of servers racks containing broken servers...\nIts as if the database is corrupted and missing parts all over\n");
        Room cerebralCortexRoom = new Room("Cerebral cortex room", "This room is grey all over.. a nice change from the flesh walls and floor..\nA strange feeling comes over me.. am i conscious?.. get it? because this room is responsible for consciousness.. get it? I am here all day\n");
        Room brainDiningRoom = new Room("Brain dining room", "This is a dining room.. within a brain? Why is there a dining room inside a brain..\n");
        Room brainKitchenRoom = new Room("Brain kitchen room", "This is a kitchen.. in a brain.. i really don't get why this exists inside here?\n");
        Room homunculusRoom = new Room("homunculus room", "Some say brains are run by small people controlling us.. There should be a small man somewhere in this room\n");
        Room brainLibraryRoom = new Room("Brain library room", "This libraries books have all been torn up and thrown all over the floor.. its hard to concentrate in here\n");

        //Exits for starting room
        Exits startingRoomExitN = new Exits("North Exit", "There doesn't seem to be an end to this exit but merely the absence of matter behind it.", 1);

        //Exits for brain entrance room
        Exits brainEntranceRoomExitS = new Exits("South exit", "There doesn't seem to be an end to this exit but merely the absence of matter behind it.", 0);
        Exits brainEntranceRoomExitN = new Exits("North exit", "The north exit has a large sign above it saying 'brain entrance'", 2);

        //Exits for brain stem room
        Exits brainStemRoomExitN = new Exits("North exit", "This exit is a large metal door", 4);
        Exits brainStemRoomExitW = new Exits("West exit", "This exit is a small dining room door..", 5);
        Exits brainStemRoomExitE = new Exits("East exit", "This exit is comprised of flesh but it wont budge.. it seems to have a slot for a key", 3, brainStemRoomKeyEW);
        Exits brainStemRoomExitS = new Exits("South exit", "This exit has a large sign above it saying 'brain exit'", 1 );

        //Exits for brain database room
        Exits brainDatabaseRoomExitW = new Exits("West exit", "This exit is comprised of flesh but it wont budge.. it seems to have a slot for a key", 2, brainStemRoomKeyEW);
        Exits brainDatabaseRoomExitE = new Exits("east exit", "This is a large wooden door with a sign saying 'library please be quiet'", 8);

        //Exits for cerebral cortex room
        Exits cerebralCortexRoomExitS = new Exits("South Exit", "This exit is a large metal door", 2);
        Exits cerebralCortexRoomExitW = new Exits("west Exit", "This is a sturdy dusty metal door it doesn't look like it is used very often", 6);
        Exits cerebralCortexRoomExitN = new Exits("north Exit", "There is no door to this exit simply a door frame", 7);

        //Exits for brain dining room
        Exits brainDiningRoomExitE = new Exits("East exit", "This exit is a small dining room door..", 2);
        Exits brainDiningRoomExitN = new Exits("north exit", "This exit is a very small metal swinging kitchen door..", 6);

        //Exits for brain kitchen room
        Exits brainKitchenRoomExitS = new Exits("south exit", "This exit is a very small metal swinging kitchen door..", 5);
        Exits brainKitchenRoomExitE = new Exits("east exit", "This is a sturdy dusty metal door it doesn't look like it is used very often", 4);

        //Exits for brain library room
        Exits brainLibraryRoomExitW = new Exits("west exit", "This is a large wooden door with a sign saying 'thank you for being quiet'", 3);

        //Exits for homunculus room
        Exits homunculusRoomExitS = new Exits("south exit", "There is no door to this exit simply a door frame", 4);

        //creating enemies
        Enemy troll = new Enemy("Troll", "The troll has a somewhat recognizable face but you can't seem to remember where from.\nHis skin is pale grey and his breath stinks of rotting flesh.\nHe looks aggressive and as if he will attack you", 8, 20, "ME NO LIKE LOOK OF YOU");
        Enemy josh = new Enemy("Josh", "This is josh.. we used to go to school together.. i think... He was annoying..\n He doesn't look happy with me.. and looks like he will attack me", 3, 20, "Hey man remember me?", joshMemory);
        Enemy digger = new Enemy("digger", "This digger looks familiar.. i feel like i might have worked with diggers but its all so hazy", 7, 30, "MWAHAHA YOU ALWAYS HATED ME", diggerMemory);

        //creating friendlies
//        Friendly wizzy = new Friendly("Wizard", "This is an ancient wizard trained in the dark arts", "Good evening peasant..", apple, 10, excalibur);

        //Adding rooms to rooms hashmap
        ge.addToRooms(startingRoom); //room 0
        ge.addToRooms(brainEntranceRoom); //room 1
        ge.addToRooms(brainStemRoom); //room 2
        ge.addToRooms(brainDatabase); //room 3
        ge.addToRooms(cerebralCortexRoom); //room 4
        ge.addToRooms(brainDiningRoom); //room 5
        ge.addToRooms(brainKitchenRoom); //room 6
        ge.addToRooms(homunculusRoom); //room 7
        ge.addToRooms(brainLibraryRoom); //room 8

        //adding entities to locations in the starting room
        startingRoom.addEntity("ne", henry, ge);
        startingRoom.addEntity("n", startingRoomExitN, ge);

        //adding entities to locations in the brain entrance room
        brainEntranceRoom.addEntity("n", brainEntranceRoomExitN, ge);
        brainEntranceRoom.addEntity("s", brainEntranceRoomExitS, ge);

        //adding entities to locations in the brain stem room
        brainStemRoom.addEntity("n", brainStemRoomExitN, ge);
        brainStemRoom.addEntity("s", brainStemRoomExitS, ge);
        brainStemRoom.addEntity("w", brainStemRoomExitW, ge);
        brainStemRoom.addEntity("e", brainStemRoomExitE, ge);
//        brainStemRoom.addEntity("w", brainStemRoomKeyEW, ge);

        //adding entities to locations in the brain database room
        brainDatabase.addEntity("w", brainDatabaseRoomExitW, ge);
        brainDatabase.addEntity("e", brainDatabaseRoomExitE, ge);

        //adding entities to locations in the cerebral cortex room
        cerebralCortexRoom.addEntity("s" ,cerebralCortexRoomExitS, ge);
        cerebralCortexRoom.addEntity("w" ,cerebralCortexRoomExitW, ge);
        cerebralCortexRoom.addEntity("n" ,cerebralCortexRoomExitN, ge);

        //adding entities to locations in the brain dining room
        brainDiningRoom.addEntity("e", brainDiningRoomExitE, ge);
        brainDiningRoom.addEntity("n", brainDiningRoomExitN, ge);

        //adding entities to locations in the brain kitchen room
        brainKitchenRoom.addEntity("s", brainKitchenRoomExitS, ge);
        brainKitchenRoom.addEntity("e", brainKitchenRoomExitE, ge);

        //adding entities to locations in the brain library room
        brainLibraryRoom.addEntity("w", brainLibraryRoomExitW, ge);

        //adding entities to locations in the homunculus room
        homunculusRoom.addEntity("s", homunculusRoomExitS, ge);

        //adding items to furniture
        desk.putItemsInFurniture(cake);
        desk.putItemsInFurniture(carrot);

        draws.putItemsInFurniture(knife);
        draws.putItemsInFurniture(turnip);

        cupboard.putItemsInFurniture(bat);
        cupboard.putItemsInFurniture(rottingApple);
        cupboard.putItemsInFurniture(rottingApple);

        chair.putItemsInFurniture(peanutMemory);

        //setting players location globally(the room) and location within that room
        player.setGlobalLocation(ge.getAllRooms().get(0));
        player.getGlobalLocation().encounterRoom();
//        player.getGlobalLocation().getPointsInRoom().get("c").encountered(player, ge);

        //running the Game Engine
        ge.run(player);
    }
}
