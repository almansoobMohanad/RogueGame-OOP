package game.LLM;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class TalkAction extends Action {

    private final DialogueCapable npc;
    private final int optionIndex;

    public TalkAction(DialogueCapable npc, int optionIndex) {

        this.npc = npc;
        this.optionIndex = optionIndex;

    }

    @Override
    public String execute(Actor actor, GameMap map) {

        return npc.handleDialogueChoice(optionIndex);

    }

    @Override
    public String menuDescription(Actor actor) {
        return "Say to " + npc + ": " + npc.getCurrentDialogue().getCurrentOptions().get(optionIndex);
    }
}
