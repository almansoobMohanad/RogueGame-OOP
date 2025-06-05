package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;
import java.util.TreeMap;

public class StandardNPCController implements NPCController {

    @Override
    public Action playturn(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map, Display display) {
        for (Behaviour behaviour : new TreeMap<>(behaviours).values()) {

            Action action = behaviour.getAction(actor, map);

            if (action != null){
                return action;
            }

        }

        return new DoNothingAction();
    }
}
