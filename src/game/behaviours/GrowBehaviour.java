package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.BedOfChaos;
import game.actors.creatures.Branch;

import java.util.List;

public class GrowBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        BedOfChaos boss = (BedOfChaos) actor;
        Display display = new Display();

        List<Branch> oldBranches = boss.getBranches();

        boss.growChain(display);

        for (Branch b : oldBranches) {
            b.chainGrow(display);
        }

        return new DoNothingAction();
    }
}