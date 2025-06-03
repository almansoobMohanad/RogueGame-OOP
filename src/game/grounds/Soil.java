package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.items.edibles.NightingaleBerry;
import game.timemanagement.Phases;
import game.timemanagement.TimeAware;

import java.util.Random;

/**
 * A class representing the soil in the valley
 * @author Adrian Kristanto
 *
 * Modified by Adji Ilhamhafiz Sarie Hakim
 */
public class Soil extends Ground implements TimeAware {
    private Phases currentPhase;
    private final Random random;
    private static final int NIGHTINGALE_BERRY_SPAWN_CHANCE = 25;


    public Soil() {
        super('.', "Soil");
        this.addCapability(GroundStatus.PLANTABLE);
        this.random = new Random();
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        if (this.currentPhase == Phases.NIGHT){
            if (random.nextInt(100) < NIGHTINGALE_BERRY_SPAWN_CHANCE) {
                location.addItem(new NightingaleBerry());
            }
        }
    }

    @Override
    public void onTimeChange(Phases newPhase) {
        currentPhase = newPhase;
    }
}
