package game.timemanagement;

import edu.monash.fit2099.engine.displays.Display;

public class TimeTracker implements TimeProvider {
    private static final int TURNS_PER_DAY = 10;
    private TimePhase currentPhase;
    private int turnCount;

    public TimeTracker() {
        this.currentPhase = new DayPhase();
        this.turnCount = 0;
    }


    public void tick() {
        turnCount++;
        if (turnCount % TURNS_PER_DAY == 0) {
            currentPhase = currentPhase.nextPhase();
            new Display().println("Time has changed to: " + currentPhase.getTimePhase());
        }
    }

    @Override
    public Phases getCurrentPhase() {
        return currentPhase.getTimePhase();
    }
}
