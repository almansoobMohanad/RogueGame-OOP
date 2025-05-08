package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.WanderBehaviour;

import java.util.*;

public abstract class NPC extends Actor {

    private HashMap<String, String> dialogues;
    private Map<Integer, Behaviour> behaviours;
    private Random random;

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
        this.random = new Random();
    }

    public void addDialogue(String key, String dialogue) {
        dialogues.put(key, dialogue);
    }

    public HashMap<String, String> getDialogues() {
        return dialogues;
    }

    public Random getRandom() {
        return random;
    }

    public void addBehaviour(int priority, Behaviour behaviour) {
        behaviours.put(priority, behaviour);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();
    }

    public abstract String sayDialogue(Actor actor, GameMap map);
}
