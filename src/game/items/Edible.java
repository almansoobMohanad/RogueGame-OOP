package game.items;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;

public interface Edible {
    String eat(Player player, GameMap map);
}
