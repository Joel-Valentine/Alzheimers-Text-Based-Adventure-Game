package jv.Entities.Friendlies;

import jv.Entities.Entity;
import jv.Mechanics.GameEngine;
import jv.Player.Player;

/**
 * Created by joelvalentine on 16/02/2016.
 */
public class Henry extends Entity{

    public Henry(String s, String s1) {
        setNameOfEntity(s);
        setDescOfEntity(s1);
        setInstructs("yes OR no");
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        boolean answered = false;
        System.out.println("He begins to hop towards you\n" + "Hi i am " + getNameOfEntity() + "\n" + getNameOfEntity() + " " + getDescOfEntity() + "\n" + "You're not from around here, are you?\n" + "Type 'yes' OR 'no' to answer\n");
        p.input();
        while(!answered)
        if(p.getInput().equals("no")){
            System.out.println("Fine i shall walk you through this mad place and all the FUN we can have");
            ge.instructions();
            answered = true;
        }else if(p.getInput().equals("yes")){
            System.out.println("Oh... i see... fine... i shall leave then\n" + "He hops away back to the corner of the room\n");
            answered = true;
        }else{
            instructions(getInstructs());
            p.input();
        }
    }
}

