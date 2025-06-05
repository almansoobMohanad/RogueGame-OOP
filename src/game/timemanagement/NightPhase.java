package game.timemanagement;

public class NightPhase implements TimePhase {

    @Override
    public Phases getTimePhase() {
        return Phases.NIGHT;
    }

    @Override
    public TimePhase nextPhase() {
        return new DayPhase();
    }
}
