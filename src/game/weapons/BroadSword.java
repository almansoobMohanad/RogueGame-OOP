package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;

public class BroadSword extends WeaponItem implements Buyable {

    private int cost;

    public BroadSword(int cost) {

        super("Broadsword", 'b', 30, "slashes", 50);
        this.cost = cost;

    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void onPurchase(Actor buyer) {

        buyer.heal(10);

    }
}
