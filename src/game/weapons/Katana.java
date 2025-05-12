package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;

public class Katana extends WeaponItem implements Buyable {

    private int cost;

    public Katana(int cost) {

        super("Katana", 'j', 50, "delivers", 60);
        this.cost = cost;

    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void onPurchase(Actor buyer) {
        buyer.hurt(25);
    }
}
