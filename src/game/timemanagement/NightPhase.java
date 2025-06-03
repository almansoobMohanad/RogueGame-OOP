package game.timemanagement;

public class NightPhase implements TimePhase {

    @Override
    public Phase getTimePhase() {
        return Phase.NIGHT;
    }

    @Override
    public TimePhase nextPhase() {
        return new DayPhase();
    }
}
