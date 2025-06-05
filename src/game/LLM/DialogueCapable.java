package game.LLM;

public interface DialogueCapable {

    Dialogue getCurrentDialogue();

    void resetDialogue();

    String handleDialogueChoice(int optionIndex);

}
