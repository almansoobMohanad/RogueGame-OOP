package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class InventoryCondition implements Condition{
    private final InvConditions condition;

    public InventoryCondition(InvConditions condition) {
        this.condition = condition;
    }

    @Override
    public boolean isSatisfied(Actor actor, GameMap map) {
        switch (condition) {
            case EMPTY:
                return actor.getItemInventory().isEmpty();
            default:
                return false;
        }
    }
}
