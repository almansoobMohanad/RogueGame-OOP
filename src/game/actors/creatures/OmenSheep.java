package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Curable;
import game.actions.CureAction;
import game.actors.Ability;
import game.actors.Status;
import game.behaviours.WanderBehaviour;
import game.grounds.plants.Inheritree;

/**
 * A passive creature that wanders around the map.
 *
 * <p>Represents an "Omen Sheep" with moderate health and wandering behaviour. The Omen Sheep
 * has a limited lifespan represented by a rot countdown attribute. If not cured in time,
 * the sheep will disappear from the map.</p>
 *
 * <p>This creature does not engage in combat or perform any special actions aside from wandering.</p>
 *
 * @author Mohanad Al-Mansoob
 */
public class OmenSheep extends Creature implements Curable {


    /**
     * Constructor for the Omen Sheep.
     * <p>Initializes the Omen Sheep with a name, display character, hit points,
     * wandering behaviour, and a rot countdown attribute.</p>
     */
    public OmenSheep() {
        super("Omen Sheep", 'm', 75);
        addBehaviour(0, new WanderBehaviour());
        addAttribute(CreatureAttributes.ROT_COUNTDOWN, new BaseActorAttribute(15));
    }

    /**
     * Handles the sheep's behaviour each turn.
     * <p>Decreases the rot countdown. If the countdown reaches zero, the sheep
     * disappears from the map. Otherwise, continues normal wandering behaviour.</p>
     *
     * @param actions list of possible actions
     * @param lastAction the previous action performed
     * @param map the map the actor is on
     * @param display the display to output messages
     * @return the action to perform for the turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        modifyAttribute(CreatureAttributes.ROT_COUNTDOWN, ActorAttributeOperations.DECREASE, 1);

        if (getAttribute(CreatureAttributes.ROT_COUNTDOWN) < 1){

            map.removeActor(this);
            display.println(this + " has disappeared.");
            return new DoNothingAction();

        }

        return super.playTurn(actions, lastAction, map, display);

    }

    /**
     * Returns a string representation of the Omen Sheep,
     * including its remaining rot countdown.
     *
     * @return a string describing the sheep and its countdown
     */
    @Override
    public String toString() {
        return super.toString() + " (" +
                this.getAttribute(CreatureAttributes.ROT_COUNTDOWN) + "/" +
                this.getAttributeMaximum(CreatureAttributes.ROT_COUNTDOWN) +
                ")";
    }

    /**
     * Cures the Omen Sheep by planting an {@link Inheritree} at adjacent locations.
     *
     * <p>If the adjacent location is enterable or contains an actor, it will
     * be replaced with an Inheritree.</p>
     *
     * @param actor the actor performing the cure
     * @param location the location of the sheep being cured
     */
    @Override
    public void cure(Actor actor, Location location) {

        for (Exit exit : location.getExits()) {

            Location adjacent = exit.getDestination();

            if (adjacent.canActorEnter(actor)) {

                adjacent.setGround(new Inheritree());

            }

            //Check the ground the actor is on
            if (adjacent.containsAnActor()) {
                adjacent.setGround(new Inheritree());
            }

        }

    }

    /**
     * Returns a list of actions that other actors can perform on this sheep.
     *
     * <p>If the other actor has the {@link Ability#CURE} capability, they are allowed to
     * perform a {@link CureAction} on the Omen Sheep.</p>
     *
     * @param otherActor the actor interacting with this sheep
     * @param direction the direction of the other actor relative to this sheep
     * @param map the map the sheep is on
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actionList = super.allowableActions(otherActor, direction, map);

        Location location = map.locationOf(this);

        if (otherActor.hasCapability(Ability.CURE)) {
            actionList.add(new CureAction(this, location));
        }

        return actionList;
    }
}
