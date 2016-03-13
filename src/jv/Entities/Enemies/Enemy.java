package jv.Entities.Enemies;

import jv.Entities.Entity;
import jv.Mechanics.GameEngine;
import jv.Player.Player;

/**
 * Created by joelvalentine on 13/03/2016.
 */
public class Enemy extends Entity {

    public Enemy(String s, String s1){
        setNameOfEntity(s);
        setDescOfEntity(s1);
    }

    private int health = 100;

    @Override
    public void encountered(Player p, GameEngine ge){
        System.out.println("You encounter a " + getNameOfEntity() + "!\n" + getDescOfEntity());
    }

    public void attack(){

    }
}
