package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;

public class TeleportationCircle extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     * @param name
     */
    public TeleportationCircle(char displayChar, String name) {
        super('A', "Teleportation Circle");
        //maybe add a teleport enum
    }
}
