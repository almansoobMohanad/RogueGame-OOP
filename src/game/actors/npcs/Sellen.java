package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.actors.Merchant;
import game.weapons.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sellen extends NPC implements Merchant {

    private final List<Buyable> sellItems = new ArrayList<>();

    /**
     * Constructor for Sellen
     */
    public Sellen() {
        super("Sellen", 's', 150);
        this.addDialogue("0","The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        this.addDialogue("1","You sense it too, don’t you? The Glintstone hums, even now.");

        sellItems.add(new BroadSword(100));
        sellItems.add(new DragonslayerGreatsword(1500));
        sellItems.add(new Katana(500));

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String sayDialogue(Actor actor, GameMap map) {
        int size = this.getDialogues().size();

        try {
            Random random = this.getRandom();
            String randKey = String.valueOf(random.nextInt(size));
            return this.getDialogues().get(randKey);
        } catch (IndexOutOfBoundsException e) {
            return "...(silence)";
        }
    }

    @Override
    public ActionList getSellActions() {

        ActionList actionList = new ActionList();

        for (Buyable item: sellItems) {

            actionList.add(new BuyAction(item, this));

        }

        return actionList;

    }

    @Override
    public void decideEffect(Buyable item, Actor buyer) {

        if (item instanceof BroadSword) {

            int previousMaxHealth = buyer.getAttributeMaximum(BaseActorAttributes.HEALTH);

            buyer.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, previousMaxHealth + 20);
            
        } else if (item instanceof DragonslayerGreatsword) {

            System.out.println("GOLDEN BEETLE SHOULD SPAWN");
            
        } else if (item instanceof Katana) {

            buyer.heal(10);
            System.out.println("OMEN SHEEP SHOULD SPAWN");
            
        }
        
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actionList = new ActionList();

        actionList.add(getSellActions());

        return actionList;
    }
}
