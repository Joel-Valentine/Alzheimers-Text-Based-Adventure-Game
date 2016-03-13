package jv.Entities.Enemies;

import jv.Mechanics.GameEngine;
import jv.Mechanics.ScrollingText;
import jv.Player.Player;

/**
 * Created by joelvalentine on 13/03/2016.
 */
public class Troll extends Enemy {

    public Troll(String s, String s1) {
        super(s,s1);
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        System.out.println("You encounter a " + getNameOfEntity() + "!\n" + getDescOfEntity() + "\nMe NO LIKE THE LOOKS OF YOU\n" + "He looks aggressive and as if he will attack you\n");
    }
}
