package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.plants.Bloodrose;

/**
 * A class representing the Bloodrose Seed item.
 * <p>
 * This class extends the abstract {@link Seed} class and represents a seed
 * that, when planted, will create a {@link Bloodrose} at the specified location.
 * The actor planting the seed will also be hurt and lose a significant amount of stamina.
 * </p>
 *
 * @author Mohanad Al-Mansoob
 */
public class BloodroseSeed extends Seed {

    /**
     * Constructor for creating a BloodroseSeed object.
     * <p>
     * This constructor initializes the seed with the name "Bloodrose Seed" and a
     * display character '*' to represent it in the game.
     * </p>
     */
    public BloodroseSeed() {

        super("Bloodrose Seed", '*');

    }

    /**
     * Plants the Bloodrose at the specified location.
     * <p>
     * This method places a Bloodrose on the ground at the given location and causes the
     * actor to be hurt by 5 points. It also decreases the stamina of the actor by 75 points
     * as a cost for planting the Bloodrose seed.
     * </p>
     *
     * @param actor the actor who is planting the seed
     * @param location the location where the Bloodrose should be planted
     */
    public void plant(Actor actor, Location location){

        location.setGround(new Bloodrose());
        actor.hurt(5);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 75);

    }

}
