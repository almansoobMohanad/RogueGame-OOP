package game.timemanagement;

public class DayPhase implements TimePhase {

    @Override
    public Phases getTimePhase() {
        return Phases.DAY;
    }

    @Override
    public TimePhase nextPhase() {
        return new NightPhase();
    }
}
