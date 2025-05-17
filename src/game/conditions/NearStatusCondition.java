package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

public class NearStatusCondition implements Condition {
    private final Actor actor;
    private final Enum<?> status;
    private final Location itemLocation;

    public NearStatusCondition(Actor actor, Enum<?> status) {
        this.actor = actor;
        this.status = status;
        this.itemLocation = null;
    }

    public NearStatusCondition(Location itemLocation, Enum<?> status) {
        this.actor = null;
        this.status = status;
        this.itemLocation = itemLocation;
    }

    @Override
    public boolean isSatisfied(Actor actor, Location location) {

        assert location != null;

        for (Exit exit : location.getExits()) {
            Location point = exit.getDestination();

            if (point.getGround().hasCapability(this.status)) {
                return true;
            }

            for (Item item : point.getItems()) {
                if (item.hasCapability(this.status)) {
                    return true;
                }
            }

            if (point.containsAnActor() && point.getActor().hasCapability(this.status)) {
                return true;
            }
        }

        return false;
    }
}