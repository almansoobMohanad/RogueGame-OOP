package game.actors.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class LowMoneyCondition implements Monologue {
    private final String message;
    private final int money;

    public LowMoneyCondition(String message, int money) {
        this.message = message;
        this.money = money;
    }

    @Override
    public String listen(Actor actor, GameMap map) {
        if (actor.getBalance() < this.money) {
            return this.message;
        }
        return null;
    }
}
