package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.effects.EffectsList;

public class Katana extends WeaponItem implements Buyable {

    private int cost;
    private EffectsList effectsList;

    public Katana(int cost, EffectsList effectsList) {

        super("Katana", 'j', 50, "delivers", 60);
        this.cost = cost;
        this.effectsList = effectsList;

    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void onPurchase(Actor buyer, Merchant merchant, GameMap gameMap) {
        buyer.hurt(25);
        effectsList.apply(buyer, gameMap);
    }
}
