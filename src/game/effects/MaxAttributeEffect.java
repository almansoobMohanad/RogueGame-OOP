package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.GameMap;

public class MaxAttributeEffect implements Effect {

    private final int amount;
    private final Enum<?> attribute;

    public MaxAttributeEffect(Enum<?> attribute, int amount) {

        this.amount = amount;
        this.attribute = attribute;

    }

    @Override
    public void apply(Actor actor, GameMap gameMap) {
        if (amount > 0) {
            actor.modifyAttributeMaximum(attribute, ActorAttributeOperations.INCREASE, amount);
        } else if (amount < 0) {
            actor.modifyAttributeMaximum(attribute, ActorAttributeOperations.DECREASE, -amount);
        }

    }
}
