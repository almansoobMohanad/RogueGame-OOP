package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public class EmptyInventoryCondition implements Condition{

    public EmptyInventoryCondition() {
    }

    @Override
    public boolean isSatisfied(Actor actor, Location location) {
        return actor.getItemInventory().isEmpty();
    }
}
