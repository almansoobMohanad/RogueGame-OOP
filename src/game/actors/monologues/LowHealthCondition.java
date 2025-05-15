package game.actors.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

public class LowHealthCondition implements Monologue {
    private final String message;
    private final int health;

    public LowHealthCondition(String message, int health) {
        this.message = message;
        this.health = health;
    }

    @Override
    public String listen(Actor actor, GameMap map) {
        if (actor.getAttribute(BaseActorAttributes.HEALTH) < this.health) {
            return this.message;
        }
        return null;
    }
}
