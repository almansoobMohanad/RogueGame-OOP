package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.NPC;

public class ListenAction extends Action {

    private NPC npc;

    public ListenAction(NPC npc) {
        this.npc = npc;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.npc.sayDialogue(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + this.npc;
    }
}
