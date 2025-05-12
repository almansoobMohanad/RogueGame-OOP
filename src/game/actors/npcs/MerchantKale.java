package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.actors.Merchant;
import game.grounds.GroundStatus;
import game.weapons.BroadSword;
import game.weapons.Buyable;
import game.weapons.DragonslayerGreatsword;

import java.util.ArrayList;
import java.util.List;

public class MerchantKale extends NPC implements Merchant {

    private final List<Buyable> sellItems = new ArrayList<>();

    /**
     * Constructor for MerchantKale
     */
    public MerchantKale() {
        super("Merchant Kale", 'k', 200);
        this.addDialogue("less", "Ah, hard times, I see. Keep your head low and your blade sharp.");
        this.addDialogue("empty", "Not a scrap to your name? Even a farmer should carry a trinket or two.");
        this.addDialogue("cursed", "Rest by the flame when you can, friend. These lands will wear you thin.");
        this.addDialogue("default", "A merchant’s life is a lonely one. But the roads… they whisper secrets to those who listen.");

        sellItems.add(new BroadSword(150));
        sellItems.add(new DragonslayerGreatsword(1700));

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
                return this.getDialogues().get("empty");
            } else if (actor.getBalance() < 500) {
                return this.getDialogues().get("less");
            } else if (nearCursedGround) {
                return this.getDialogues().get("cursed");
            } else {
                return this.getDialogues().get("default");
            }
        } catch (IndexOutOfBoundsException e) {
            return "...(silence)";
        }
    }

    @Override
    public ActionList getSellActions() {

        ActionList actionList = new ActionList();

        for (Buyable item : sellItems) {

            actionList.add(new BuyAction(item, this));

        }

        return actionList;

    }

        @Override
    public void decideEffect(Buyable item, Actor buyer) {

        if (item instanceof BroadSword){

            buyer.modifyAttributeMaximum(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 30);

        }

        else if(item instanceof DragonslayerGreatsword){

            buyer.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 20);

        }


    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actionList = new ActionList();

        actionList.add(getSellActions());

        return actionList;
    }


}
