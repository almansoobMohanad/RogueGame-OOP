package game.timemanagement;

public interface TimePhase {
    Phases getTimePhase();
    TimePhase nextPhase();
}
