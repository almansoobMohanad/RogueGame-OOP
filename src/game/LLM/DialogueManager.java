package game.LLM;

public class DialogueManager {

    private final LLMService llmService;
    private final JsonDialogueParser parser;

    public DialogueManager(LLMService llmService) {

        this.llmService = llmService;
        this.parser = new JsonDialogueParser();

    }

    public Dialogue generateDialogue(String npcPrompt) throws DialogueParsingException {
        // 1. Prepend only‐JSON instruction:
        String fullPrompt = """
            You must reply ONLY in valid JSON. Do not include any additional text.
            
            %s
            """.formatted(npcPrompt);

        // 2. Send that wrapped prompt to the LLM
        String rawJson = llmService.generateText(fullPrompt);

        // 3. Parse whatever came back (expecting JSON) into a Dialogue
        return parser.parse(rawJson);
    }

}
