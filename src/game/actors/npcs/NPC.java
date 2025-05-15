package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.monologues.Monologue;

import java.util.*;

public abstract class NPC extends Actor {

    private final List<Monologue> monologuePool;
    private final Map<Integer, Behaviour> behaviours;

    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public NPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours = new TreeMap<>();
        this.monologuePool = new ArrayList<>();
    }

    public void addIntoMonologuePool(Monologue dialogue) {
        monologuePool.add(dialogue);
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

    public List<Monologue> sayMonologue(Actor actor, GameMap map){
        List<Monologue> availableMonologues = new ArrayList<>();
        for (Monologue monologue : this.monologuePool) {
            if (monologue.canListen(actor, map)) {
                availableMonologues.add(monologue);
            }
        }
        return availableMonologues;
    }
}
