package game.timemanagement;

import edu.monash.fit2099.engine.positions.Location;

public interface TimeAware {
    void onTimeChange(Location location);
}
