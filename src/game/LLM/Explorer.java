package game.LLM;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.NPC;
import game.behaviours.NPCController;

import java.util.List;
import java.util.Scanner;

public class Explorer extends NPC implements DialogueCapable {

    private static final String EXPLORER_PROMPT_PREFIX = """
    You are "The Explorer", a wise wanderer NPC in Elden Ring Universe.
    
    When a player asks you a question about the game world, you answer in a lore-accurate and immersive way. 
    
    You must respond in this exact strict JSON format:
    
    {
      "options": [["Ask a question"]],
      "responses": [["Your lore-rich response to the player's question."]]
    }
    
    Requirements:
    - Do not ask questions back.
    - The lore response must be fully contained in one string inside the responses array.
    - Use poetic, worldbuilding-friendly language to match a fantasy RPG tone.
    - Assume the player's question comes after this prompt, and answer it directly within the JSON structure.
    
    Player question:
    """;

    private NPCController controller;
    private final DialogueManager dialogueManager;
    private Dialogue dialogue;

    public Explorer(NPCController controller, DialogueManager dialogueManager) {

        super("The Explorer", 'E', 45, controller);
        this.controller = controller;
        this.dialogueManager = dialogueManager;
        resetDialogue();

    }

    @Override
    public Dialogue getCurrentDialogue() {
        return this.dialogue;

    }

    @Override
    public void resetDialogue() {
        // This is a "stub" greeting just to start the interaction
        this.dialogue = new Dialogue(
                List.of(List.of("Hey Explorer, can I ask you something?")),
                List.of(List.of("")) // Placeholder; overwritten after first real question
        );
    }

    @Override
    public String handleDialogueChoice(int optionIndex) {

        ExtendedDisplay reader = new ExtendedDisplay();
        String input = reader.readLine("Enter Question:");

        String prompt = EXPLORER_PROMPT_PREFIX + input;

        resetDialogue();

        try {

            Dialogue newDialogue = dialogueManager.generateDialogue(prompt);
            return newDialogue.getResponseFor(0);

        } catch (DialogueParsingException e) {
            return "The Explorer blinks, unsure how to respond.";
        }

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
