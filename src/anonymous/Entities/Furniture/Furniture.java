package anonymous.Entities.Furniture;

import anonymous.Entities.Entity;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

/**
 * Created by joelvalentine on 22/03/2016.
 */
public class Furniture extends Entity{

    public Furniture(String nameOfEntity, String descOfEntity){
        setNameOfEntity(nameOfEntity);
        setDescOfEntity(descOfEntity);
        setInstructs("Type 'search' to search this " + getNameOfEntity() + " for items\nType 'leave' to leave and go somewhere else\n");
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        System.out.println("\nYou see a " + getNameOfEntity() + " " + getDescOfEntity() + "\n");
        System.out.println(getInstructs());
        setAnswered(false);
        while(!isAnswered()){
            p.input();
            if(p.getInput().equals("search")){
                System.out.println("Ey b0ss Fuk u mayn");
            }else if(p.getInput().equals("leave")){
                System.out.println("\nYou decide to leave and go somewhere else\n");
                setAnswered(true);
            }
        }
    }

}
