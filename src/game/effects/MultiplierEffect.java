package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class MultiplierEffect implements Effect{

    private final float amount;

    public MultiplierEffect(float amount) {
        this.amount = amount;
    }
    @Override
    public void apply(Actor actor, GameMap gameMap) {
        actor.increaseDamageMultiplier(this.amount);
    }
}
