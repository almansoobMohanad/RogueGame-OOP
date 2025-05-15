package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.behaviours.WanderBehaviour;
import game.grounds.GroundStatus;

import java.util.List;

import static game.actors.monologues.Monologues.*;

public class MerchantKale extends NPC {
    /**
     * Constructor for MerchantKale
     */
    public MerchantKale() {
        super("Merchant Kale", 'k', 200);
        this.addBehaviour(0, new WanderBehaviour());
        this.addIntoMonologuePool(KALE_DEFAULT.getMessage());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String sayDialogue(Actor actor, GameMap map) {
        boolean nearCursedGround = false;
        List<Exit> exits = map.locationOf(this).getExits();

        for (Exit exit : exits) {
            if (exit.getDestination().getGround().hasCapability(GroundStatus.CURSED)) {
                nearCursedGround = true;
                break;
            }
        }

        try {
            if (actor.getItemInventory().isEmpty()) {
                this.addIntoMonologuePool(KALE_EMPTY.getMessage());
            }
            if (actor.getBalance() < 500) {
                this.addIntoMonologuePool(KALE_LESS.getMessage());
            }
            if (nearCursedGround) {
                this.addIntoMonologuePool(KALE_CURSED.getMessage());
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
