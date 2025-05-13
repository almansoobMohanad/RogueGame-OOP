package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.behaviours.WanderBehaviour;

import java.util.List;
import java.util.Random;

public class Sellen extends NPC {
    /**
     * Constructor for Sellen
     */
    public Sellen() {
        super("Sellen", 's', 150);
        this.addBehaviour(0, new WanderBehaviour());
        this.addIntoMonologuePool("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        this.addIntoMonologuePool("You sense it too, don’t you? The Glintstone hums, even now.");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String sayDialogue(Actor actor, GameMap map) {
        try {
            return this.getMonologuePool().get(this.getRandom().nextInt(this.getMonologuePool().size()));
        } catch (IndexOutOfBoundsException e) {
            return "...(silence)";
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(actor, direction, map);
        actions.add(new ListenAction(this));
        return actions;
    }
}
