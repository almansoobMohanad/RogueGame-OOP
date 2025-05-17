package game.items.eggs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.conditions.Condition;
import game.effects.Effect;


/**
 * Abstract representation of an egg that is laid on the ground,
 * hatches into a creature based on hatching conditions, and
 * be eaten by actors if stored in inventory.
 *
 * <p>
 * Subclasses define their own hatching logic.
 * </p>
 *
 * @author Arielle Ocampo
 */
public class Egg extends Item implements Edible {

    /**
     * Counts the number of consecutive turns egg has spent
     * on the ground (upon being laid/dropped).
     *
     */
    private int hatchCounter = 0;
    private final Actor hatchling;
    private Condition hatchCondition;
    private final Effect eatEffect;

    /**
     * Constructs a new Egg.
     *
     * @param name the name to display for egg
     * @param displayChar the character to represent egg on map
     */
    public Egg(String name, char displayChar, Actor hatchling,
               Condition hatchCondition, Effect eatEffect) {
        super(name, displayChar, true);
        this.hatchling = hatchling;
        this.hatchCondition = hatchCondition;
        this.eatEffect = eatEffect;
    }

    public void setHatchCondition(Condition condition) {
        this.hatchCondition = condition;
    }

    /**
     * Called once per turn when the egg is on the map.
     * Increments hatchCounter and hatches the egg when ready.
     *
     * @param location current location of egg on map
     */
    @Override
    public void tick(Location location) {
            hatchCounter++;

        if (hatchCondition.isSatisfied(null, location.map()) && !location.containsAnActor()) {
                location.addActor(hatchling);
                location.removeItem(this);
            }
        }

    public int getHatchCounter() {
        return hatchCounter;
    }

    /**
     * Called when an actor eats egg from its inventory.
     * Subclasses apply their effects and logic.
     *
     * @param actor actor eating the egg
     * @param map game map context
     * @return description of the eating action
     */
    @Override
    public String eat(Actor actor, GameMap map) {
        eatEffect.apply(actor, map);
        actor.removeItemFromInventory(this);
        return actor + " eats the egg.";
    }

    /**
     * Only allows EatAction if this egg is currently held in the actor's inventory.
     *
     * @param actor actor performing the action
     * @param location current location
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location) {
        ActionList actions = super.allowableActions(actor, location);

        if (actor.getItemInventory().contains(this)) {
            actions.add(new EatAction(this));
        }
        return actions;
    }
}
