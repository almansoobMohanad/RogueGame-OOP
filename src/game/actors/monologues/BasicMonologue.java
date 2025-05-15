package game.actors.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class BasicMonologue implements Monologue {
    private final String message;

    public BasicMonologue(String message) {
        this.message = message;
    }

    @Override
    public String listen(Actor actor, GameMap map) {
        return this.message;
    }
}
