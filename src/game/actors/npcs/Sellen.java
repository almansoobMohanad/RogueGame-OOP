package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actors.monologues.Monologue;
import game.behaviours.WanderBehaviour;

import static game.actors.monologues.Monologues.SELLEN1;
import static game.actors.monologues.Monologues.SELLEN2;

public class Sellen extends NPC {
    /**
     * Constructor for Sellen
     */
    public Sellen() {
        super("Sellen", 's', 150);
        this.addBehaviour(0, new WanderBehaviour());
        this.addIntoMonologuePool(new Monologue(SELLEN1.getMessage()));
        this.addIntoMonologuePool(new Monologue(SELLEN2.getMessage()));
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public ActionList allowableActions(Actor actor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(actor, direction, map);
        actions.add(new ListenAction(this));
        return actions;
    }
}
