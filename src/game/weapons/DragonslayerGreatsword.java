package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.effects.EffectsList;

public class DragonslayerGreatsword extends WeaponItem implements Buyable {

    private int cost;
    private EffectsList effectsList;

    public DragonslayerGreatsword(int cost, EffectsList effectsList) {

        super("Dragonslayer Greatsword", 'D', 70, "strikes", 75);
        this.cost = cost;
        this.effectsList = effectsList;

    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void onPurchase(Actor buyer, Merchant merchant, GameMap gameMap) {

        buyer.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 15);
        effectsList.apply(buyer, gameMap);

    }
}
