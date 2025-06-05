package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class TeleportAction extends Action {
    private final GameMap destinationMap;
    private final Location destinationLocation;

    public TeleportAction(GameMap destinationMap, Location destinationLocation) {
        this.destinationMap = destinationMap;
        this.destinationLocation = destinationLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        destinationMap.addActor(actor, destinationLocation);
        return actor + " teleports to " + destinationLocation.x() + "," + destinationLocation.y();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to new location at (" +
                destinationLocation.x() + ", " + destinationLocation.y() + ") in " +
                destinationLocation.map();
    }
}
