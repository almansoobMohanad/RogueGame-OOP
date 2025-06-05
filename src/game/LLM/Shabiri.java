package game.LLM;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.NPC;
import game.behaviours.NPCController;

import java.util.List;

public class Shabiri extends NPC implements DialogueCapable {

    private final String DIALOGUE_PROMPT = """
    You are Shabiri, an ancient, cryptic entity in a fantasy world. You enjoy deep, evolving philosophical conversations with adventurers. The player always begins the dialogue.
    
    Generate a STRICTLY JSON object representing a 3-round dialogue initiated by the player. The structure should follow this format exactly:
    
    {
      "options": [
        ["Player greeting + question 1", "Player greeting + question 2", "Player greeting + question 3"],       // Round 1 (player starts)
        ["Reflective follow-up 1", "Thoughtful follow-up 2", "Emotionally reactive follow-up 3"],              // Round 2
        ["Final remark or insight 1", "Final remark or insight 2", "Final remark or insight 3"]                // Round 3
      ],
      "responses": [
        ["Shabiri's reply to option 1", "Reply to option 2", "Reply to option 3"],   // Round 1
        ["Reply to follow-up 1", "Reply to follow-up 2", "Reply to follow-up 3"],   // Round 2
        ["Final reply 1", "Final reply 2", "Final reply 3"]                         // Round 3
      ]
    }
    
    Tone and Conversation Guidelines:
    - The greeting from Shabiri should be poetic, mystical, or unsettling in a calm way.
    - The player's **first round** options should each begin like a greeting or invitation to speak, followed by a deep or existential question.
        - e.g., "Greetings, Shabiri. What do you see when you look at the stars?"
    - The **second round** should feel like the player is engaging with Shabiri's earlier response — either agreeing, reflecting, or challenging it thoughtfully.
        - e.g., "That’s fascinating... but does that mean we have no free will?"
    - The **final round** should be conclusive or emotionally weighted — a parting thought, final insight, or personal reflection.
    - Shabiri's replies should evolve naturally, becoming deeper, more enigmatic, or more personal with each round.

    """;

    private final NPCController controller;
    private final DialogueManager dialogueManager;
    private Dialogue dialogue;

    public Shabiri(NPCController controller, DialogueManager dialogueManager){

        super("Shabiri", 'Q', 76, controller);

        this.controller = controller;
        this.dialogueManager = dialogueManager;
        this.dialogue = fetchNewDialogue();
    }

    @Override
    public Dialogue getCurrentDialogue() {
        return this.dialogue;
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
                            List.of("Hi"),
                            List.of("What?"),
                            List.of("What did you say?")
                    ),
                    List.of(
                            List.of("Seems like not a good day to talk to you!"),
                            List.of("Ha?"),
                            List.of("Bla bla bla ba...")
                    )
            );
        }

    }

    @Override
    public String handleDialogueChoice(int optionIndex) {

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
