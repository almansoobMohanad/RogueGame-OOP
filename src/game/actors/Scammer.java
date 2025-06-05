package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Scammer {
    void onScam(Actor scammedTarget, GameMap map);
}
