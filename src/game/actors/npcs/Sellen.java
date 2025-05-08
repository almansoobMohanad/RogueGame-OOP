package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

public class Sellen extends NPC {
    /**
     * Constructor for Sellen
     */
    public Sellen() {
        super("Sellen", 's', 150);
        this.addDialogue("0","The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        this.addDialogue("1","You sense it too, don’t you? The Glintstone hums, even now.");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String sayDialogue(Actor actor, GameMap map) {
        int size = this.getDialogues().size();

        try {
            Random random = this.getRandom();
            String randKey = String.valueOf(random.nextInt(size));
            return this.getDialogues().get(randKey);
        } catch (IndexOutOfBoundsException e) {
            return "...(silence)";
        }
    }
}
