package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.boss.BedOfChaos;
import game.actors.creatures.boss.GrowAction;

public class GrowBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        BedOfChaos boss = (BedOfChaos) actor;
        return new GrowAction(boss);
    }
}