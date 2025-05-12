package game.actors.creatures;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CureAction;
import game.actions.EatAction;
import game.actors.Ability;
import game.behaviours.WanderBehaviour;
import game.items.eggs.Edible;
import game.items.eggs.GoldenEgg;
import game.items.eggs.OmenSheepEgg;

public class GoldenBeetle extends Creature implements Reproductive, Edible {

    private int eggLayCounter = 0;


    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        addBehaviour(0, new WanderBehaviour());
    }

    /**
     * Lays a new Golden Beetle Egg when the counter reaches threshold.
     *
     * @param map the game map of Omen Sheep
     * @param location the location of Omen Sheep on the map
     */
    @Override
    public void reproduce(GameMap map,Location location) {
        eggLayCounter++;

        if (eggLayCounter >= 5) {
            location.addItem(new GoldenEgg());
            eggLayCounter = 0;
        }
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = super.allowableActions(otherActor, direction, map);
        actionList.add(new EatAction(this));

        return actionList;
    }

    @Override
    public String eat(Actor actor, GameMap map) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 15);
        actor.addBalance(1000);
        map.removeActor(this);
        return actor + " eats the beetle.";
    }
}
