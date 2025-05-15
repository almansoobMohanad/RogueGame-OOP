package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class NearStatusCondition implements Condition {
    private final Actor actor;
    private final Enum<?> status;

    public NearStatusCondition(Actor actor, Enum<?> status) {
        this.actor = actor;
        this.status = status;
    }

    @Override
    public boolean isSatisfied(Actor actor, GameMap map) {
        Location location = map.locationOf(this.actor);

        for (Exit exit : location.getExits()) {
            if (exit.getDestination().getGround().hasCapability(this.status)) {
                return true;
            }
        }

        return false;
    }
}
