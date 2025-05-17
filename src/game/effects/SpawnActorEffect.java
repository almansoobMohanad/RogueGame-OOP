package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An {@link Effect} that spawns a specified {@link Actor} near another reference actor.
 * <p>
 * The reference actor can be explicitly provided, or defaults to the buyer actor if null.
 * The actor will be spawned in a neighboring location that can accommodate it.
 * </p>
 *
 * @author Mohanad Al-Mansoob
 */
public class SpawnActorEffect implements Effect {

    /**
     * The actor to be spawned on the map.
     */
    private final Actor actorToSpawn;

    /**
     * The reference actor near which the new actor will spawn.
     * If null, the actor who performed the action is used as reference.
     */
    private final Actor referenceActor; // null = near buyer

    /**
     * Constructor to create a SpawnActorEffect.
     *
     * @param actorToSpawn the actor to spawn
     * @param referenceActor the actor to spawn near (null to default to the actor who performed the action)
     */
    public SpawnActorEffect(Actor actorToSpawn, Actor referenceActor) {
        this.actorToSpawn = actorToSpawn;
        this.referenceActor = referenceActor;
    }

    /**
     * Applies the effect by spawning the actor near the reference actor.
     *
     * @param actor the actor (used as default reference if referenceActor is null)
     * @param gameMap the game map where the actor will be spawned
     */
    @Override
    public void apply(Actor actor, GameMap gameMap) {
        Actor reference = (referenceActor != null) ? referenceActor : actor;

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

