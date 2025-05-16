package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.WanderBehaviour;
import game.conditions.InvConditions;
import game.conditions.InventoryCondition;
import game.conditions.MoneyCondition;
import game.conditions.NearStatusCondition;
import game.actions.BuyAction;
import game.actors.Merchant;
import game.effects.AttributeEffect;
import game.effects.EffectsList;
import game.effects.MaxAttributeEffect;
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
        this.addBehaviour(0, new WanderBehaviour());
        this.addIntoMonologuePool(new Monologue("A merchant’s life is a lonely one. But the roads… they whisper secrets to those who listen."));
        this.addIntoMonologuePool(new Monologue(new NearStatusCondition(this, GroundStatus.CURSED), "Rest by the flame when you can, friend. These lands will wear you thin."));
        this.addIntoMonologuePool(new Monologue(new InventoryCondition(InvConditions.EMPTY), "Not a scrap to your name? Even a farmer should carry a trinket or two."));
        this.addIntoMonologuePool(new Monologue(new MoneyCondition(500, false), "Ah, hard times, I see. Keep your head low and your blade sharp."));


        EffectsList broadSwordEffects = new EffectsList();
        broadSwordEffects.addEffect(new MaxAttributeEffect(BaseActorAttributes.STAMINA, 30));
        sellItems.add(new BroadSword(150, broadSwordEffects));

        EffectsList dragonslayerGreatswordEffects = new EffectsList();
        dragonslayerGreatswordEffects.addEffect(new AttributeEffect(BaseActorAttributes.STAMINA, 20));
        sellItems.add(new DragonslayerGreatsword(1700, dragonslayerGreatswordEffects));

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
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
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actionList = super.allowableActions(otherActor, direction, map);

        actionList.add(getSellActions());

        return actionList;
    }


}
