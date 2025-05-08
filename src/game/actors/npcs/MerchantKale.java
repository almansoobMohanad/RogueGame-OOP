package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class MerchantKale extends NPC {
    /**
     * Constructor for MerchantKale
     */
    public MerchantKale() {
        super("Merchant Kale", 'k', 200);
        this.addDialogue("less", "Ah, hard times, I see. Keep your head low and your blade sharp.");
        this.addDialogue("empty", "Not a scrap to your name? Even a farmer should carry a trinket or two.");
        this.addDialogue("cursed", "Rest by the flame when you can, friend. These lands will wear you thin.");
        this.addDialogue("default", "A merchant’s life is a lonely one. But the roads… they whisper secrets to those who listen.");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
