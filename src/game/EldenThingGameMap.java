package game;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import game.actors.npcs.SuspiciousMerchant;
import game.behaviours.StandardNPCController;
import game.timemanagement.Phases;
import game.timemanagement.TimeTracker;

import java.util.List;

public class EldenThingGameMap extends GameMap {
    private final TimeTracker timeTracker;
    public EldenThingGameMap(String name, GroundFactory groundFactory, List<String> lines, TimeTracker timeTracker) {
        super(name, groundFactory, lines);
        this.timeTracker = timeTracker;
    }

    public void tick() {
        if (timeTracker != null) {
            timeTracker.tick();

            if (timeTracker.getCurrentPhase() == Phases.NIGHT) {
                this.at(20, 14).addActor(new SuspiciousMerchant(new StandardNPCController()));
            }
        }
        super.tick();
    }
}
