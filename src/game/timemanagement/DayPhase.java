package game.timemanagement;

public class DayPhase implements TimePhase {

    @Override
    public Phase getTimePhase() {
        return Phase.DAY;
    }

    @Override
    public TimePhase nextPhase() {
        return new NightPhase();
    }
}
