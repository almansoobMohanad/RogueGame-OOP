package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.actors.Ability;
import game.actors.Status;
import java.util.ArrayList;
import java.util.List;

public class TeleportationCircle extends Ground {
    private final List<TeleportDestination> destinations = new ArrayList<>();


    public TeleportationCircle() {
        super('A', "Teleportation Circle");
        //maybe add a teleport enum
    }

    public void addDestination(TeleportDestination destination) {
        destinations.add(destination);
    }


    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Only provide teleport actions if the actor is STANDING on this tile (not adjacent)
        if (location.getActor() == actor && actor.hasCapability(Ability.TELEPORT)) { //could also add enum for teleportable ground but ig this reduces depedencies
            for (TeleportDestination dest : destinations) {
                actions.add(new TeleportAction(dest.getMap(), dest.getLocation()));
            }
        }

        return actions;
    }

}
