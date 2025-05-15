package game.actors.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Monologue {
    String listen(Actor actor, GameMap map);
}
