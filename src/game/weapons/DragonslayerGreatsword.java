package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

public class DragonslayerGreatsword extends WeaponItem implements Buyable {

    private int cost;

    public DragonslayerGreatsword(int cost) {

        super("Dragonslayer Greatsword", 'D', 70, "strikes", 75);
        this.cost = cost;

    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void onPurchase(Actor buyer) {

        buyer.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 15);

    }
}
