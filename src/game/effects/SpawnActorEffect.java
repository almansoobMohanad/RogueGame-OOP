package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class SpawnActorEffect implements Effect {

    private final Actor actorToSpawn;
    private final Actor referenceActor; // null = near buyer

    public SpawnActorEffect(Actor actorToSpawn, Actor referenceActor) {
        this.actorToSpawn = actorToSpawn;
        this.referenceActor = referenceActor;
    }

    @Override
    public void apply(Actor buyer, GameMap gameMap) {
        Actor reference = (referenceActor != null) ? referenceActor : buyer;

        Location locationReference = gameMap.locationOf(reference);

        for (Exit exit : locationReference.getExits()) {
            if (exit.getDestination().canActorEnter(actorToSpawn)) {
                gameMap.addActor(actorToSpawn, exit.getDestination());
                break;
            }
        }

        System.out.println(actorToSpawn + " spawns near " + reference);

    }
}

