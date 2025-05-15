package game.actors.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class EmptyInventoryCondition implements Monologue{
    private final String message;

    public EmptyInventoryCondition(String message) {
        this.message = message;
    }

    @Override
    public String listen(Actor actor, GameMap map) {
        if (actor.getItemInventory().isEmpty()) {
            return this.message;
        }
        return null;
    }
}
