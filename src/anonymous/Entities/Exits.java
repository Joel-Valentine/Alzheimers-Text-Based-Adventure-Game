package anonymous.Entities;

import anonymous.Player.Player;
import anonymous.Mechanics.GameEngine;

/**
 * Created by joelvalentine on 11/03/2016.
 */
public class Exits extends Entity{

    public Exits(String s, String s1, int tr) {
        setTr(tr);
        setNameOfEntity(s);
        setDescOfEntity(s1);
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
                p.setGlobalLocation(ge.getAllRooms().get(getTr()));
                System.out.println(p.getGlobalLocation().getContext().toString());
                setAnswered(true);
            } else if (p.getInput().equals("no")) {
                leave();
            } else {
                System.out.println(getInstructs());
            }
        }
    }
}
