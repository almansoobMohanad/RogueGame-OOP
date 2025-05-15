package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.monologues.Monologue;
import game.actors.monologues.MonologuePool;
import game.behaviours.WanderBehaviour;

import java.util.*;

public abstract class NPC extends Actor {

    private final List<String> monologuePool;
    private final Map<Integer, Behaviour> behaviours;
    private final Random random;
    private final MonologuePool monologuePool1;

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
        this.random = new Random();
        this.monologuePool1 = new MonologuePool();
    }

    public void addMonologue(Monologue dialogue) {
        monologuePool1.addMonologue(dialogue);
    }

    public void addIntoMonologuePool(String dialogue) {
        monologuePool.add(dialogue);
    }

    public List<String> getMonologuePool() {
        return monologuePool;
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

    public String sayMonologue(Actor actor, GameMap map) {
        return monologuePool1.listen(actor, map);
    }

    public abstract String sayDialogue(Actor actor, GameMap map);
}
