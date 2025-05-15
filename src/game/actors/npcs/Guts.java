package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actors.monologues.Monologue;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.conditions.HealthCondition;
import game.weapons.BareFist;

import static game.actors.monologues.Monologues.*;

public class Guts extends NPC {
    /**
     * Constructor for Guts
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.setIntrinsicWeapon(new BareFist());
        this.addBehaviour(0, new AttackBehaviour(50));
        this.addBehaviour(1, new WanderBehaviour());
        this.addIntoMonologuePool(new Monologue(GUTS1.getMessage()));
        this.addIntoMonologuePool(new Monologue(GUTS2.getMessage()));
        this.addIntoMonologuePool(new Monologue(new HealthCondition(50, false), GUTS_WEAK.getMessage()));
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
