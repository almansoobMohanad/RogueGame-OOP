package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomNPCController implements NPCController{

    private final Random rand = new Random();

    @Override
    public Action playturn(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map, Display display) {
        if (behaviours.isEmpty()) {
            return new DoNothingAction();
        }

        List<Behaviour> behaviourList = new ArrayList<>(behaviours.values());
        Behaviour chosenBehaviour = behaviourList.get(rand.nextInt(behaviourList.size()));

        Action action = chosenBehaviour.getAction(actor, map);
        return action != null ? action : new DoNothingAction();

    }
}
