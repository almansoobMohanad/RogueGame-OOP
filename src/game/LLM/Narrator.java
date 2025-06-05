package game.LLM;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.NPC;
import game.behaviours.NPCController;

import java.util.List;

public class Narrator extends NPC implements DialogueCapable {

    private final String DIALOGUE_PROMPT = """
            You are a mystical storyteller NPC named "The Narrator" in a fantasy game.
            Generate a Strictly JSON object representing a 3-part short story dialogue with the player. The structure should match this format:

            {
              "options": [
                ["Tell me a story"]
                ["Continue", "Decline"],   // Round 1
                ["Continue", "Decline"],   // Round 2
                ["Finish", "Decline"]      // Round 3
              ],
              "responses": [
                ["Would you like to hear a story about...]
                ["Story part 1 if Continue", "Reply if Decline"],
                ["Story part 2 if Continue", "Reply if Decline"],
                ["Story part 3 if Finish", "Reply if Decline"]
              ]
            }

            Each response should tell one part of a unique, cohesive story. The player should be able to choose to continue or decline at each step.
            """;

    private final NPCController controller;
    private final DialogueManager dialogueManager;
    private Dialogue dialogue;

    public Narrator(NPCController controller, DialogueManager dialogueManager) {

        super("The Narrator", 'N', 200, controller);
        this.controller = controller;
        this.dialogueManager = dialogueManager;
        this.dialogue = fetchNewDialogue();

    }

    @Override
    public void resetDialogue() {
        this.dialogue = fetchNewDialogue();
    }

    private Dialogue fetchNewDialogue() {

        try {
            return dialogueManager.generateDialogue(DIALOGUE_PROMPT);
        } catch (DialogueParsingException e) {
            e.printStackTrace();

            return new Dialogue(
                    List.of(
                            List.of("…", "…"),
                            List.of("…", "…"),
                            List.of("…", "…")
                    ),
                    List.of(
                            List.of("…", "…"),
                            List.of("…", "…"),
                            List.of("…", "…")
                    )
            );
        }

    }

    @Override
    public Dialogue getCurrentDialogue() {
        return this.dialogue;
    }

    @Override
    public String handleDialogueChoice(int optionIndex) {

        String chosenText = dialogue.getCurrentOptions().get(optionIndex);

        if (chosenText.equalsIgnoreCase("Decline")) {
            String declineResponse = dialogue.getResponseFor(optionIndex);
            resetDialogue();
            return declineResponse;
        }

        String response = dialogue.getResponseFor(optionIndex);
        dialogue.nextRound();

        if (dialogue.isFinished()) {
            resetDialogue();
        }

        return response;



    }

    @Override
    public ActionList allowableActions(Actor actor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(actor, direction, map);


        for (int i = 0; i < dialogue.getCurrentOptions().size(); i++) {
            actions.add(new TalkAction(this, i));
        }

        return actions;

    }
}
