package game.actors.creatures.boss;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Display;

public class GrowAction extends Action {

    private final BedOfChaos boss;

    public GrowAction(BedOfChaos boss) {
        this.boss = boss;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Display display = new Display();
        boss.grow(display);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}

