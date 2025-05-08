package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.WanderBehaviour;

import java.util.*;

public abstract class NPC extends Actor {

    private HashMap<String, String> dialogues;
    private Map<Integer, Behaviour> behaviours;

    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public NPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.dialogues = new HashMap<>();
        this.behaviours = new TreeMap<>();
        this.behaviours.put(0, new WanderBehaviour());
    }

    public void addDialogue(String key, String dialogue) {
        dialogues.put(key, dialogue);
    }

    public HashMap<String, String> getDialogues() {
        return dialogues;
    }

//    public abstract String dialogueChoice();
}
