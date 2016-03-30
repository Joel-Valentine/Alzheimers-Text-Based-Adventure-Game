package anonymous.Mechanics;

import anonymous.Entities.Items.Item;
import anonymous.Room.Room;
import anonymous.Player.Player;

import java.util.HashMap;

/**
 * Created by anonymous on 11/03/2016.
 */
public class GameEngine {

    private HashMap<Integer, Room> allRooms = new HashMap<>();
    private HashMap<Integer, Item> allItemsInGame = new HashMap<>();
    private HashMap<Integer, Item> allMemoriesInGame = new HashMap<>();
    private String difficultySetting;
    private long stopTime;
    private long startTime;
    private long totalTime;

    public GameEngine(Player player) {
        System.out.println("\nPlease play game with terminal/cmd window maximised/as large as it goes\n");
        difficultySetting(player);
    }

    public void instructions(){
        System.out.println("\nTo win the game collect all " + getAllMemoriesInGame().size() + " of the memories. You will loose if your health hits 0 and if your energy hits 0\nTo move around inside of a room type any of the following " + getAllRooms().get(0).getPointsInRoom().keySet() + " 'c' being the centre\nWhen you encounter an exit you will have the choice to go through to another room with 'yes' or 'no', this is how you move between rooms\nTo access your inventory type 'inventory'\nTo get your current room information type 'get current room'\nTo get your current player stats type 'get player status'\nAll other instructions will be told to you when you encounter something, now go play the game!\n");
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

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public String getDifficultySetting() {
        return difficultySetting;
    }

    public void setDifficultySetting(String difficultySetting) {
        this.difficultySetting = difficultySetting;
    }

    public void difficultySetting(Player player){
        boolean answered = false;
        while(!answered){
            System.out.println("What difficulty would you like? choose 'normal' or 'easy'");
            player.input();
            if(player.getInput().equals("easy")){
                setDifficultySetting(player.getInput());
                answered = true;
            }else if(player.getInput().equals("normal")){
                setDifficultySetting(player.getInput());
                answered = true;
            }
        }
        System.out.println();
    }

    public void run(Player player) {
        setStartTime(System.currentTimeMillis());
        while(!player.isAlive(this) && !player.hasWon(this)){
            player.move(this);
        }
        setStopTime(System.currentTimeMillis());
        setTotalTime((getStopTime() - getStartTime())/1000);
        System.out.print("\nyou survived for " + getTotalTime() + " seconds, your final stats were: ");
        player.currentStatus(this);
    }
}
