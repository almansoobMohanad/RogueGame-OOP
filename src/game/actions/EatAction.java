package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.eggs.Edible;

public class EatAction extends Action {
    private final Edible edible;

    public EatAction(Edible edible) {
        this.edible = edible;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return edible.eat(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + edible;
    }
}
