package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;

public interface Buyable {

    int getCost();
    void onPurchase(Actor buyer, Merchant merchant, GameMap gameMap);

}
