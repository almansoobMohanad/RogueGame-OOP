package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CureAction;
import game.actions.EatAction;
import game.actors.Ability;
import game.behaviours.FollowBehaviour;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.conditions.NearStatusCondition;
import game.conditions.TurnCounterCondition;
import game.effects.AttributeEffect;
import game.grounds.GroundStatus;
import game.items.eggs.Edible;
import game.items.eggs.Egg;

public class GoldenBeetle extends Creature implements Reproductive, Edible {

    private int eggLayCounter = 0;
    private Actor followTarget = null;


    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        addBehaviour(0, new ReproduceBehaviour(this));
        addBehaviour(2, new WanderBehaviour());
    }


//    @Override
//    public void reproduce(GameMap map,Location location) {
//        eggLayCounter++;
//        if (eggLayCounter >= 5) {
//            location.addItem(new GoldenEgg());
//            eggLayCounter = 0;
//
//        }
//    }

    @Override
    public void reproduce(GameMap map, Location location) {
        eggLayCounter++;

        if (eggLayCounter >= 5) {
            Egg egg = new Egg(
                    "Golden Egg",
                    '0',
                    new GoldenBeetle(),
                    new NearStatusCondition(GroundStatus.CURSED),
                    new AttributeEffect(BaseActorAttributes.STAMINA, 20));

                    location.addItem(egg);
            System.out.println("Golden Beetle laid an egg at " + location);
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
        actor.heal(15);
        actor.addBalance(1000);
        map.removeActor(this);
        return actor + " eats the beetle.";
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (followTarget == null || !map.contains(followTarget)) {
            // Scan surroundings for a followable actor
            Location here = map.locationOf(this);
            for (Exit exit : here.getExits()) {
                Location adjacent = exit.getDestination();
                Actor other = adjacent.getActor();
                if (other != null && other.hasCapability(Ability.FOLLOWABLE)) {
                    followTarget = other;
                    this.addBehaviour(1, new FollowBehaviour(followTarget));
                    break;
                }
            }
        }

        return super.playTurn(actions, lastAction, map, display);

    }
}
