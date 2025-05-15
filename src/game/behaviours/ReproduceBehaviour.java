package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.Reproductive;

public class ReproduceBehaviour implements Behaviour {
    private final Reproductive reproductive;

    public ReproduceBehaviour(Reproductive reproductive){
        this.reproductive = reproductive;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        int itemCountBefore = location.getItems().size();

        reproductive.reproduce(map, location);

        int itemCountAfter = location.getItems().size();
        if (itemCountAfter > itemCountBefore) {
            return new DoNothingAction();
        }

        return null; // Allow other behaviours like wandering to run
    }

}
