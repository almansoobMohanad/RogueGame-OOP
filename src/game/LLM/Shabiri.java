package game.LLM;

import game.actors.npcs.NPC;
import game.behaviours.NPCController;

public class Shabiri extends NPC {

    private final NPCController controller;

    public Shabiri(NPCController controller){

        super("Shabiri", 'z', 76, controller);
        this.controller = controller;
    }



}
