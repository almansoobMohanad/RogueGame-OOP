package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;


public class TeleportDestination {
    private final GameMap map;
    private final Location location;

    public TeleportDestination(GameMap map, Location location) {
        this.map = map;
        this.location = location;
    }

    public GameMap getMap() { return map; }
    public Location getLocation() { return location; }
}
