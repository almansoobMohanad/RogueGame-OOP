package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.items.eggs.Egg;

public class TurnCounterCondition implements Condition {

    private final Egg egg;
    private final int turnThreshold;


    public TurnCounterCondition(Egg egg, int turnThreshold) {
        this.egg = egg;
        this.turnThreshold = turnThreshold;
    }

    @Override
    public boolean isSatisfied(Actor actor, Location location) {
        return egg.getHatchCounter() >= turnThreshold;
    }
}
