package anonymous.Entities.Friendlies;

import anonymous.Entities.Enemies.Enemy;
import anonymous.Entities.Entity;
import anonymous.Mechanics.GameEngine;
import anonymous.Player.Player;

/**
 * Created by joelvalentine on 16/03/2016.
 */
public class Friendly extends Entity {

    private String introductionText;
    private String encounterText;
    private Entity questEntity;
    private int questPointsRecieved;
    private boolean questComplete;
    private boolean questInProgress;

    public Friendly(String nameOfEntity, String descOfEntity, String encounterText, Entity quest, int questPointsRecieved){
        setNameOfEntity(nameOfEntity);
        setDescOfEntity(descOfEntity);
        setEncounterText(encounterText);
        setQuestEntity(quest);
        setQuestPointsRecieved(questPointsRecieved);
    }

    @Override
    public void encountered(Player p, GameEngine ge){
        System.out.println("\nYou encounter a " + getNameOfEntity() + "!\n" +  getDescOfEntity() + "\n\n" + getNameOfEntity() + ": " + getEncounterText());
        setAnswered(false);
        while(!isAnswered()){
            if(isQuestInProgress() && getQuestEntity().getClass().getName().contains("Enemy")){
                questInvolvingEnemy(p);
                setAnswered(true);
            }else if(isQuestInProgress() && getQuestEntity().getClass().getName().contains("Item")){
                questInvolvingItem(p);
                setAnswered(true);
            }else if(isQuestComplete()){
                System.out.println("\nYou have already completed the " + getNameOfEntity() + "'s quest\n");
                setAnswered(true);
            }else if(isInteractable()){
                interact(p);
            }else{
                p.input();
                if(p.getInput().equals("leave")){
                    leave();
                }
            }
        }
    }

    private void interact(Player p) {
        if(getQuestEntity().getClass().getName().contains("Enemy")){
            System.out.println(getNameOfEntity() + ": If you kill the " + getQuestEntity().getNameOfEntity() + " i shall give you a reward\n\nDo you accept type 'yes' or 'no'\n");
            p.input();
            if(p.getInput().equals("yes")){
                questInvolvingEnemy(p);
                setAnswered(true);
                setQuestInProgress(true);
            }else if(p.getInput().equals("no")){
                System.out.println("\nYou decide not to take on this quest and go somewhere else\n");
                setAnswered(true);
            }
        }else if(getQuestEntity().getClass().getName().contains("Item")){
            System.out.println(getNameOfEntity() + ": If you give me a/an " + getQuestEntity().getNameOfEntity() + " i shall give you a reward\n\nDo you accept type 'yes' or 'no'\n");
            p.input();
            if(p.getInput().equals("yes")) {
                questInvolvingItem(p);
                setQuestInProgress(true);
                setAnswered(true);
            }else if(p.getInput().equals("no")){
                System.out.println("\nYou decide not to take on this quest and go somewhere else\n");
                setAnswered(true);
            }
        }
    }

    public void questInvolvingEnemy(Player p){
        Entity castingToEnemy = getQuestEntity();
        Enemy questEnemy = (Enemy) castingToEnemy;
        if(!questEnemy.isAlive()){
            setQuestComplete(true);
            setQuestInProgress(false);
            p.setQuestPoints(p.getQuestPoints() + getQuestPointsRecieved());
            System.out.println("\nWell done you have slain the " + getQuestEntity().getNameOfEntity() + " you now have " + p.getQuestPoints() + " quest point/s\n");
            setAnswered(true);
        }else{
            System.out.println("\nYou must kill the " + getQuestEntity().getNameOfEntity() + " once you have done this return to me for your reward.. Now go..\n");
            setAnswered(true);
        }
    }

    public void questInvolvingItem(Player p){
        if(p.getInventory().containsKey(getQuestEntity().getNameOfEntity())){
            System.out.println("\nYou do have a/an " + getQuestEntity().getNameOfEntity() + " in your inventory if you want to give the " + getNameOfEntity() + " your " + getQuestEntity().getNameOfEntity() + " type '" + getQuestEntity().getNameOfEntity()+ "'\nType 'leave' if you want to keep your " + getQuestEntity().getNameOfEntity() + " and go somewhere else\n");
            p.input();
            if(p.getInput().equals(getQuestEntity().getNameOfEntity())){
                setQuestComplete(true);
                setQuestInProgress(false);
                p.setQuestPoints(p.getQuestPoints() + getQuestPointsRecieved());
                System.out.println("\nYou decide to give your " + getQuestEntity().getNameOfEntity() + " to the " + getNameOfEntity() + " you now have " + p.getQuestPoints() + " quest points\n");
                p.removeItemFromInventory(getQuestEntity().getNameOfEntity());
                setAnswered(true);
            }else if(p.getInput().equals("leave")){
                System.out.println("\nYou decide not to give your " + getQuestEntity().getNameOfEntity() + " to the " + getNameOfEntity() + " and move somewhere else\n");
                setAnswered(true);
            }
        }else{
            System.out.println("\nYou do not have a/an " + getQuestEntity().getNameOfEntity() + " in your inventory.. go find one and come back to complete the quest\n");
            setAnswered(true);
            setQuestInProgress(true);
        }

    }

    public boolean isInteractable(){
        if(getQuestEntity() == null){
            return false;
        }else{
            return true;
        }
    }

    public String getEncounterText() {
        return encounterText;
    }

    public void setEncounterText(String encounterText) {
        this.encounterText = encounterText;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public Entity getQuestEntity() {
        return questEntity;
    }

    public void setQuestEntity(Entity questEntity) {
        this.questEntity = questEntity;
    }

    public boolean isQuestComplete() {
        return questComplete;
    }

    public void setQuestComplete(boolean questComplete) {
        this.questComplete = questComplete;
    }

    public boolean isQuestInProgress() {
        return questInProgress;
    }

    public void setQuestInProgress(boolean questInProgress) {
        this.questInProgress = questInProgress;
    }

    public int getQuestPointsRecieved() {
        return questPointsRecieved;
    }

    public void setQuestPointsRecieved(int questPointsRecieved) {
        this.questPointsRecieved = questPointsRecieved;
    }
}
