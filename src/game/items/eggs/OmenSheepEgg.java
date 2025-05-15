package game.items.eggs;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.OmenSheep;

/**
 * An egg laid by an Omen Sheep that hatches in
 * the right conditions (after fixed amount of turns on ground).
 * Can be eaten if picked up to restore health.
 *
 * @author Arielle Ocampo
 */
public class OmenSheepEgg extends Egg {

    /**
     * Constructs a new Omen Sheep Egg.
     */
    public OmenSheepEgg() {
        super("Omen Sheep Egg", '0');
    }

    /**
     * Checks whether hatchCounter has reached threshold to hatch.
     *
     * @param location the tile egg is currently on
     * @return true once counter reaches threshold
     */
    @Override
    public boolean canHatch(Location location) {
        return hatchCounter >= 3 && !location.containsAnActor();
    }

    /**
     * Spawns a new Omen Sheep at egg’s location,
     * removing egg from the map.
     *
     * @param map the game map containing the egg
     * @param location the tile where the egg hatches
     */
    @Override
    public void hatch(GameMap map, Location location) {
        location.addActor(new OmenSheep());
    }

    /**
     * Applies eating effects when egg is eaten from inventory,
     * increasing maximum health by 10.
     *
     * @param actor the actor eating the egg
     * @param map game map
     * @return description of eating action
     */
    @Override
    public String eat(Actor actor, GameMap map) {

        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 10);

        actor.removeItemFromInventory(this);
        return actor + " eats the egg.";
    }

}
