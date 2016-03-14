package jv;

import jv.Player.Player;
import jv.Mechanics.GameEngine;

/**
 * Created by joelvalentine on 16/02/2016.
 */
public class main {

    public static void main(String args[]) {

        //Constants
        Player player = new Player();
        GameEngine ge = new GameEngine(player);

        //running the Game Engine
        ge.run(player);

        //debugging
        //System.out.println(player.getGlobalLocation());
    }
}
