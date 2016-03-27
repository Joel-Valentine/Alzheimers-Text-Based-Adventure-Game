package anonymous.Entities;

import anonymous.Player.Player;
import anonymous.Mechanics.GameEngine;

/**
 * Created by joelvalentine on 11/03/2016.
 */
public class Exits extends Entity{

    public Exits(String nameOfEntity, String descOfEntity, int roomNo) {
        setRoom(roomNo);
        setNameOfEntity(nameOfEntity);
        setDescOfEntity(descOfEntity);
        setInstructs("\nType 'yes' to go through the " + getNameOfEntity() + "\n" + "Type 'no' to move somewhere else\n");
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        System.out.println("\nThis is the " + getNameOfEntity() + "\n" + getDescOfEntity());
        setAnswered(false);
        System.out.println(getInstructs());
        while(!isAnswered()){
        p.input();
            if (p.getInput().equals("yes")) {
                System.out.println("\nYou step through the " + getNameOfEntity());
                p.energyDepletion();
                System.out.println("Your energy is now " + p.getEnergy() + "\n");
                p.setGlobalLocation(ge.getAllRooms().get(getRoom()));
                System.out.println("This is the " + p.getGlobalLocation().getNameOfRoom() + "\n" + p.getGlobalLocation().getContext().toString());
                setAnswered(true);
            } else if (p.getInput().equals("no")) {
                leave();
            } else {
                System.out.println(getInstructs());
            }
        }
    }
}
