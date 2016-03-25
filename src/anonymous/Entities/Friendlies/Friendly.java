package anonymous.Entities.Friendlies;

import anonymous.Entities.Entity;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

/**
 * Created by joelvalentine on 16/03/2016.
 */
public class Friendly extends Entity {

    private String encounterText;
    private boolean interactable;
    private String interactText;

    public Friendly(String nameOfEntity, String descOfEntity, String encounterText, boolean interact, String interactText){
        setNameOfEntity(nameOfEntity);
        setDescOfEntity(descOfEntity);
        setEncounterText(encounterText);
        setInteractable(interact);
        setInteractText(interactText);
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        while(!isAnswered()){
            System.out.println(getEncounterText());
        }
    }

    public String getEncounterText() {
        return encounterText;
    }

    public void setEncounterText(String encounterText) {
        this.encounterText = encounterText;
    }

    public boolean isInteractable() {
        return interactable;
    }

    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    public String getInteractText() {
        return interactText;
    }

    public void setInteractText(String interactText) {
        this.interactText = interactText;
    }
}
