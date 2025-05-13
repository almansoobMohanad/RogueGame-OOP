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
import game.actors.creatures.OmenSheep;
import game.actors.creatures.SpiritGoat;
import game.effects.AttributeEffect;
import game.effects.EffectsList;
import game.effects.MaxAttributeEffect;
import game.effects.SpawnActorEffect;
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

        EffectsList broadSwordEffects = new EffectsList();
        broadSwordEffects.addEffect(new MaxAttributeEffect(BaseActorAttributes.HEALTH, 20));
        sellItems.add(new BroadSword(100, broadSwordEffects));

        EffectsList dragonslayerGreatswordEffects = new EffectsList();
        // this should be GoldenBeetle
        dragonslayerGreatswordEffects.addEffect(new SpawnActorEffect(new SpiritGoat(), null));
        sellItems.add(new DragonslayerGreatsword(1500, dragonslayerGreatswordEffects));

        EffectsList katanaEffects = new EffectsList();
        katanaEffects.addEffect(new SpawnActorEffect(new OmenSheep(), this));
        katanaEffects.addEffect(new AttributeEffect(BaseActorAttributes.HEALTH, 10));
        katanaEffects.addEffect(new MaxAttributeEffect(BaseActorAttributes.STAMINA, 20));
        sellItems.add(new Katana(500, katanaEffects));

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
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actionList = new ActionList();

        actionList.add(getSellActions());

        return actionList;
    }
}
