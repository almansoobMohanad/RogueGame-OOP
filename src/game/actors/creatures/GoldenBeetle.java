package game.actors.creatures;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.WanderBehaviour;

public class GoldenBeetle extends Creature implements Reproductive {


    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        addBehaviour(0, new WanderBehaviour());
    }

    @Override
    public void reproduce(GameMap map, Location location) {

    }
}
