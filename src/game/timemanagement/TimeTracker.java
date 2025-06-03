package game.timemanagement;

import java.util.ArrayList;
import java.util.List;

public class TimeTracker{
    private final List<TimeAware> timeAwareObjects;
    private final int TURNS_PER_DAY = 5;
    private TimePhase currentPhase;
    private int turnCount;

    public TimeTracker() {
        this.timeAwareObjects = new ArrayList<>();
        this.currentPhase = new DayPhase();
        this.turnCount = 0;
    }

    public void registerTimeAware(TimeAware timeAware) {
        timeAwareObjects.add(timeAware);
    }

    public void tick() {
        turnCount++;
        if (turnCount % TURNS_PER_DAY == 0) {
            currentPhase = currentPhase.nextPhase();
            notifyTimeChange();
        }
    }

    private void notifyTimeChange() {
        for (TimeAware timeAware : timeAwareObjects) {
            timeAware.onTimeChange(currentPhase.getTimePhase());
        }
    }
}
