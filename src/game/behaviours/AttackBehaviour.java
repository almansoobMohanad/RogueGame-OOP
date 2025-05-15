package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

import java.util.ArrayList;

public class AttackBehaviour implements Behaviour {

    private int health;

    public AttackBehaviour(int health) {
        this.health = health;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {

            Location targetLoc = exit.getDestination();
            if (targetLoc.containsAnActor()) {
                Actor target = targetLoc.getActor();
                if (target.getAttribute(BaseActorAttributes.HEALTH) > this.health) {
                    actions.add(new AttackAction(target, "around"));
                }
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(0); // Return the first attack action found
        } else {
            return null; // No attack action available
        }
    }
}
