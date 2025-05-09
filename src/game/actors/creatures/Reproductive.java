package game.actors.creatures;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for creatures that can reproduce.
 * Reproduction logic is defined in each class.
 */
public interface Reproductive {
    void reproduce(GameMap map, Location location);
}
