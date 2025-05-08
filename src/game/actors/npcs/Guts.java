package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class Guts extends NPC {
    /**
     * Constructor for Guts
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.addDialogue("0", "RAAAAGH!");
        this.addDialogue("1", "I’LL CRUSH YOU ALL!");
        this.addDialogue("weak", "WEAK! TOO WEAK TO FIGHT ME!");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
