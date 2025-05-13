package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

public class AttributeEffect implements Effect {

    private final BaseActorAttributes attribute;
    private final int amount;

    public AttributeEffect(BaseActorAttributes attribute, int amount) {
        this.attribute = attribute;
        this.amount = amount;
    }

    @Override
    public void apply(Actor actor, GameMap gameMap) {

        if (actor.hasAttribute(attribute)) {

            if (amount > 0) {

                actor.modifyAttribute(attribute, ActorAttributeOperations.INCREASE, amount);

            } else if (amount < 0) {

                actor.modifyAttribute(attribute, ActorAttributeOperations.DECREASE, -amount);

            }

        }

    }

}
