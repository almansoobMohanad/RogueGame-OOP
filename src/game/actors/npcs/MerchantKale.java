package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actors.monologues.*;
import game.behaviours.WanderBehaviour;
import game.conditions.InvConditions;
import game.conditions.InventoryCondition;
import game.conditions.MoneyCondition;
import game.conditions.NearStatusCondition;
import game.grounds.GroundStatus;

import static game.actors.monologues.Monologues.*;

public class MerchantKale extends NPC {
    /**
     * Constructor for MerchantKale
     */
    public MerchantKale() {
        super("Merchant Kale", 'k', 200);
        this.addBehaviour(0, new WanderBehaviour());
        this.addIntoMonologuePool(new Monologue(KALE_DEFAULT.getMessage()));
        this.addIntoMonologuePool(new Monologue(new NearStatusCondition(this, GroundStatus.CURSED), KALE_CURSED.getMessage()));
        this.addIntoMonologuePool(new Monologue(new InventoryCondition(InvConditions.EMPTY), KALE_EMPTY.getMessage()));
        this.addIntoMonologuePool(new Monologue(new MoneyCondition(500, false), KALE_LESS.getMessage()));
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
