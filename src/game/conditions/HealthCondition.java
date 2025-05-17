package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;

public class HealthCondition implements Condition {
    private final int health;
    private final boolean higher;

    public HealthCondition(int health, boolean higher) {
        this.health = health;
        this.higher = higher;
    }

    @Override
    public boolean isSatisfied(Actor actor, Location location) {
        if (this.higher) {
            return actor.getAttribute(BaseActorAttributes.HEALTH) > this.health;
        } else {
            return actor.getAttribute(BaseActorAttributes.HEALTH) < this.health;
        }
    }
}
