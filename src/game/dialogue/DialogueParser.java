package game.dialogue;

public interface DialogueParser {

    Dialogue parse(String parserFormat) throws DialogueParsingException;

}
