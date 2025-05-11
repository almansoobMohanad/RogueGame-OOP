package game.items.eggs;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Edible {
    String eat(Actor actor, GameMap map);
}
