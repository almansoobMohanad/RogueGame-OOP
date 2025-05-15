package game.actors.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.conditions.Condition;

public class Monologue {
    private final Condition condition;
    private final String message;

    public Monologue(Condition condition, String message) {
        this.condition = condition;
        this.message = message;
    }

    public Monologue(String message) {
        this.condition = null;
        this.message = message;
    }

    public boolean canListen(Actor actor, GameMap map) {
        if (this.condition == null) {
            return true;
        }
        return this.condition.isSatisfied(actor, map);
    }

    public String listen() {
        return this.message;
    }
}
