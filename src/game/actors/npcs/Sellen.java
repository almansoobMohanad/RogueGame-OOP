package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.WanderBehaviour;
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

public class Sellen extends NPC implements Merchant {

    private final List<Buyable> sellItems = new ArrayList<>();

    /**
     * Constructor for Sellen
     */
    public Sellen() {
        super("Sellen", 's', 150);
        this.addBehaviour(0, new WanderBehaviour());
        this.addIntoMonologuePool(new Monologue("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever"));
        this.addIntoMonologuePool(new Monologue("You sense it too, don’t you? The Glintstone hums, even now."));


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
    public ActionList getSellActions() {

        ActionList actionList = new ActionList();

        for (Buyable item: sellItems) {

            actionList.add(new BuyAction(item, this));

        }

        return actionList;

    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = super.allowableActions(otherActor, direction, map);

        actions.add(getSellActions());

        return actions;
    }



}
