package anonymous.Entities.Friendlies;

import anonymous.Entities.Entity;
import anonymous.Mechanics.GameEngine;
import anonymous.Mechanics.ScrollingText;
import anonymous.Player.Player;

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
            System.out.println("\nFine i shall walk you through this mad place and all the FUN we can have");
            ge.instructions();
            System.out.println("Anyone else you meet will probably only ask for yes or no answers because anything else would be toooooooo complicated\nAs you can see.. its grrrreeeeaaaaaat fun around here\n... now go i have... some really important stuff to do.\n");
            answered = true;
        }else if(p.getInput().equals("yes")){
            System.out.println("\nOh... i see... fine... i shall leave then\n" + "He hops away back to the corner of the room\n");
            answered = true;
        }else{
            instructions(getInstructs());
            p.input();
        }
    }
}

