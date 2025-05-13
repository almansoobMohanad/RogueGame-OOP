package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.weapons.BareFist;

public class Guts extends NPC {
    /**
     * Constructor for Guts
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.setIntrinsicWeapon(new BareFist());
        this.addBehaviour(0, new AttackBehaviour(50));
        this.addBehaviour(1, new WanderBehaviour());
        this.addIntoMonologuePool("RAAAAGH!");
        this.addIntoMonologuePool("I’LL CRUSH YOU ALL!");
        this.addMonologue("weak", "WEAK! TOO WEAK TO FIGHT ME!");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String sayDialogue(Actor actor, GameMap map) {

        try {

            if (actor.getAttribute(BaseActorAttributes.HEALTH) < 50) {
                this.addIntoMonologuePool(this.getMonologues().get("weak"));
            }

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
