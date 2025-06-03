package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.timemanagement.Phase;
import game.timemanagement.TimeAware;

public class NightingaleBerry extends Item implements TimeAware {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public NightingaleBerry(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    @Override
    public void onTimeChange(Phase newPhase) {

    }
}
