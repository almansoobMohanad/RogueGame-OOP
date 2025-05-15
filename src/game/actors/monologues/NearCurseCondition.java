package game.actors.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npcs.NPC;
import game.grounds.GroundStatus;

public class NearCurseCondition implements Monologue {
    private final String message;
    private final NPC npc;

    public NearCurseCondition(String message, NPC npc) {
        this.message = message;
        this.npc = npc;
    }

    @Override
    public String listen(Actor actor, GameMap map) {
        Location location = map.locationOf(this.npc);

        for (Exit exit : location.getExits()) {
            if (exit.getDestination().getGround().hasCapability(GroundStatus.CURSED)) {
                return this.message;
            }
        }
        return null;
    }
}
