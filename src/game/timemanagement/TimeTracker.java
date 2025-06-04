package game.timemanagement;

public class TimeTracker implements TimeProvider {
    private static final int TURNS_PER_DAY = 5;
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
        }
    }

    @Override
    public Phases getCurrentPhase() {
        return currentPhase.getTimePhase();
    }
}
