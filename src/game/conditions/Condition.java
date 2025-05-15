package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Condition {
    boolean isSatisfied(Actor actor, GameMap map);
}
