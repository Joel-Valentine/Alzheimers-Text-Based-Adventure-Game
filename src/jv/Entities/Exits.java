package jv.Entities;

import jv.Player.Player;
import jv.Mechanics.GameEngine;

/**
 * Created by joelvalentine on 11/03/2016.
 */
public class Exits extends Entity{

    public Exits(String s, String s1, int tr) {
        setTr(tr);
        setNameOfEntity(s);
        setDescOfEntity(s1);
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        System.out.println("\nThis is the: " + getNameOfEntity() + "\nIts description: " + getDescOfEntity());
        System.out.println("Type 'yes' to go through\nType 'no' to do something else\n");
        p.input();
        while(!isAnswered()){
            if (p.getInput().equals("yes")) {
                System.out.println("You step through\n");
                p.energyDepletion();
                p.setGlobalLocation(ge.getAllRooms().get(getTr()));
                System.out.println(p.getGlobalLocation().getContext().toString());
                setAnswered(true);
            } else if (p.getInput().equals("no")) {
                setAnswered(true);
                System.out.println("You decide not to go through the door\n");
            } else {
                System.out.println("yes OR no\n");
                p.input();
            }
        }
    }
}
