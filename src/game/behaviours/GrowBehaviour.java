package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.BedOfChaos;

public class GrowBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        BedOfChaos boss = (BedOfChaos) actor;

        Display display = new Display();

        if (boss.getBranchCount() == 0) {
            boss.growChain(display);
        }
        else {
            boss.continueGrowChain(display);
        }

        return new DoNothingAction();
    }
}