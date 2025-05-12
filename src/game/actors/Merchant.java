package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.weapons.Buyable;

public interface Merchant {

    ActionList getSellActions();

    void decideEffect(Buyable item, Actor buyer);

}
