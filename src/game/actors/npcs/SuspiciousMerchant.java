package game.actors.npcs;

import game.behaviours.NPCController;
import game.timemanagement.Phases;
import game.timemanagement.TimeAware;

public class SuspiciousMerchant extends NPC implements TimeAware {
    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param controller
     */
    public SuspiciousMerchant(String name, char displayChar, int hitPoints, NPCController controller) {
        super(name, displayChar, hitPoints, controller);

    }

    @Override
    public void onTimeChange(Phases newPhase) {

    }
}
