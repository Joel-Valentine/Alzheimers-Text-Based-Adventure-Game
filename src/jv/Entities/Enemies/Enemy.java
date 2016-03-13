package jv.Entities.Enemies;

import jv.Entities.Entity;

/**
 * Created by joelvalentine on 13/03/2016.
 */
public class Enemy extends Entity {

    private int health = 100;

    public void encountered(){
        System.out.println("You encounter a " + getNameOfEntity() + "!\n He seems to have ");
    }

    public void attack(){

    }
}
