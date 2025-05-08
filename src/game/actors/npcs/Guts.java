package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class Guts extends NPC {
    /**
     * Constructor for Guts
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.addDialogue("0", "RAAAAGH!");
        this.addDialogue("1", "I’LL CRUSH YOU ALL!");
        this.addDialogue("weak", "WEAK! TOO WEAK TO FIGHT ME!");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    @Override
    public String sayDialogue(Actor actor, GameMap map) {
        int size = this.getDialogues().size();

        try {

            if (actor.getAttribute(BaseActorAttributes.HEALTH) < 50) {
                return this.getDialogues().get("weak");
            } else {
                String randKey = String.valueOf(this.getRandom().nextInt(size));
                return this.getDialogues().get(randKey);
            }

        } catch (IndexOutOfBoundsException e) {
            return "...(silence)";
        }
    }
}
