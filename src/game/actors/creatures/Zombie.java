package game.actors.creatures;

import game.behaviours.NPCController;
import game.timemanagement.Phases;
import game.timemanagement.TimeAware;

public class Zombie extends Creature implements TimeAware {
    /**
     * Constructor for a Creature.
     *
     * @param name        the name of the creature
     * @param displayChar the character that will represent the creature in the UI
     * @param hitPoints   the creature's initial health points
     * @param controller
     */
    public Zombie(String name, char displayChar, int hitPoints, NPCController controller) {
        super(name, displayChar, hitPoints, controller);

    }

    @Override
    public void onTimeChange(Phases newPhase) {

    }
}
