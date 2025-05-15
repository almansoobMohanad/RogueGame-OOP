package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.NPC;

import java.util.Random;

public class ListenAction extends Action {

    private final NPC npc;
    private final Random random;

    public ListenAction(NPC npc) {
        this.npc = npc;
        this.random = new Random();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.npc.sayMonologue(actor, map).get(random.nextInt(this.npc.sayMonologue(actor, map).size())).listen();
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + this.npc;
    }
}
