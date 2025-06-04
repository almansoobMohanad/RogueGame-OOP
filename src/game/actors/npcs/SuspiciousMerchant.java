package game.actors.npcs;

import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.NPCController;
import game.timemanagement.TimeAware;

public class SuspiciousMerchant extends NPC implements TimeAware {
    /**
     * The constructor of the Actor class.
     * @param controller
     */
    public SuspiciousMerchant(NPCController controller) {
        super("Suspicious Merchant", 'M', 100, controller);

    }

    @Override
    public void onTimeChange(Location location) {
    }
}
