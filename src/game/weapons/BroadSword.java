package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.effects.EffectsList;

public class BroadSword extends WeaponItem implements Buyable {

    private int cost;
    private EffectsList effectsList;

    public BroadSword(int cost, EffectsList effectsList) {

        super("Broadsword", 'b', 30, "slashes", 50);
        this.cost = cost;
        this.effectsList = effectsList;

    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void onPurchase(Actor buyer, Merchant merchant, GameMap gameMap) {

        buyer.heal(10);
        effectsList.apply(buyer, gameMap);

    }
}
