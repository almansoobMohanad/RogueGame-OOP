package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class EmptyInventoryCondition implements Condition{

    public EmptyInventoryCondition() {
    }

    @Override
    public boolean isSatisfied(Actor actor, GameMap map) {
        return actor.getItemInventory().isEmpty();
    }
}
