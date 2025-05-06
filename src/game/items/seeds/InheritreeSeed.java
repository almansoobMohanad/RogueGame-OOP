package game.items.seeds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.plants.Inheritree;

/**
 * A class representing the Inheritree Seed item.
 * <p>
 * This class extends the abstract {@link Seed} class and represents a seed
 * that, when planted, will create an {@link Inheritree} at the specified location.
 * The actor planting the seed will also lose a portion of their stamina.
 * </p>
 *
 * @author Mohanad Al-Mansoob
 */
public class InheritreeSeed extends Seed {

    /**
     * Constructor for creating an InheritreeSeed object.
     * <p>
     * This constructor initializes the seed with the name "Inheritree Seed" and a
     * display character '*' to represent it in the game.
     * </p>
     */
    public InheritreeSeed () {

        super("Inheritree Seed", '*');

    }

    /**
     * Plants the Inheritree at the specified location.
     * <p>
     * This method places an Inheritree on the ground at the given location and decreases
     * the stamina of the actor by 25. This action simulates the planting of an Inheritree seed.
     * </p>
     *
     * @param actor the actor who is planting the seed
     * @param location the location where the Inheritree should be planted
     */
    public void plant(Actor actor, Location location){

        location.setGround(new Inheritree());
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 25);

    }

    /**
     * Returns the list of allowable actions for planting this seed.
     * <p>
     * This method calls the super method to check if the ground is plantable and adds the
     * action for planting the seed if allowed.
     * </p>
     *
     * @param otherActor the actor interacting with the seed
     * @param location the location of the actor
     * @return a list of actions the actor can perform with this seed
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        return super.allowableActions(otherActor, location);
    }
}
