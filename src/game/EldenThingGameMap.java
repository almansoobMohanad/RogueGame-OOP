package game;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.actors.npcs.SuspiciousMerchant;
import game.behaviours.StandardNPCController;
import game.timemanagement.*;

import java.util.List;
import java.util.Random;

public class EldenThingGameMap extends GameMap implements TimeAware {
    private final TimeTracker timeTracker;
    private final TimeProvider timeProvider;
    private Random random;
    boolean merchantSpawned;

    public EldenThingGameMap(String name, GroundFactory groundFactory, List<String> lines) {
        super(name, groundFactory, lines);
        this.timeTracker = null;
        this.timeProvider = ServiceLocator.getTimeProvider();
        this.random = new Random();
        this.merchantSpawned = false;
    }

    public EldenThingGameMap(String name, GroundFactory groundFactory, char groundChar, int width, int height, TimeTracker timeTracker) {
        super(name, groundFactory, groundChar, width, height);
        this.timeTracker = timeTracker;
        this.timeProvider = null;
    }

    public void tick() {
        if (timeTracker != null) {
            timeTracker.tick();
        }

        if (random != null) {
            NumberRange xRange = this.getXRange();
            NumberRange yRange = this.getYRange();

            int randX = random.nextInt(xRange.min(), xRange.max());
            int randY = random.nextInt(yRange.min(), yRange.max());

            this.onTimeChange(this.at(randX, randY));
        }

        super.tick();
    }

    @Override
    public void onTimeChange(Location location) {
        if (timeProvider != null) {
            Phases currentPhase = this.timeProvider.getCurrentPhase();
            if (currentPhase == Phases.NIGHT && !merchantSpawned) {

                boolean containsActor = location.containsAnActor();

                if (!containsActor) {
                    location.addActor(new SuspiciousMerchant(new StandardNPCController()));
                }

                merchantSpawned = true;
            } else if (currentPhase == Phases.DAY) {
                merchantSpawned = false;
            }
        }
    }
}
