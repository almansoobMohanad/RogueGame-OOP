package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class MoneyCondition implements Condition {
    private final int money;
    private final boolean higher;

    public MoneyCondition(int money, boolean higher) {
        this.money = money;
        this.higher = higher;
    }

    @Override
    public boolean isSatisfied(Actor actor, GameMap map) {
        if (this.higher) {
            return actor.getBalance() > this.money;
        } else {
            return actor.getBalance() < this.money;
        }
    }
}
