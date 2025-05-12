package game.items.eggs;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.GroundStatus;

public class GoldenEgg extends Egg{


    public GoldenEgg() {
        super("Golden Egg", '0');
    }


    //can hatch will change over here- it will only hatch if surrounded by cursed and cannot hatch if picked up by farmer
    @Override
    public boolean canHatch(Location location) {

        for(Exit exit: location.getExits()){
            Location adjacent = exit.getDestination();

            if(adjacent.getGround().hasCapability(GroundStatus.CURSED)) {  //by cursed entities do they only mean ground? or could it be something else like bloodrose?
                return true;
            }
        }

        return false;
    }

    @Override
    public void hatch(GameMap map, Location location) {

    }

    @Override
    public String eat(Actor actor, GameMap map) {
        return "";
    }
}
