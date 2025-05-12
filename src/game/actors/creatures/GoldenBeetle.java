package game.actors.creatures;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.WanderBehaviour;

public class GoldenBeetle extends Creature implements Reproductive, Edible {

    private int eggLayCounter = 0;


    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        addBehaviour(0, new WanderBehaviour());
    }

    /**
     * Lays a new Golden Beetle Egg when the counter reaches threshold.
     *
     * @param map the game map of Omen Sheep
     * @param location the location of Omen Sheep on the map
     */
    @Override
    public void reproduce(GameMap map,Location location) {
        eggLayCounter++;

        if (eggLayCounter >= 5) {
            location.addItem(new GoldenEgg());
            eggLayCounter = 0;
        }
    }
}
