package game.LLM;

import java.util.List;

public class Dialogue {

    private final List<List<String>> options;   // Player options per round
    private final List<List<String>> responses; // NPC responses per round
    private int roundIndex = 0;

    public Dialogue(List<List<String>> options, List<List<String>> responses) {
        this.options = options;
        this.responses = responses;
    }


    public List<String> getCurrentOptions() {
        if (roundIndex < options.size()) {
            return options.get(roundIndex);
        }
        return List.of(); // No more options
    }

    public String getResponseFor(int optionIndex) {
        if (roundIndex < responses.size() && optionIndex < responses.get(roundIndex).size()) {
            return responses.get(roundIndex).get(optionIndex);
        }
        return "Shhh... we will talk later";
    }

    public void nextRound() {
        roundIndex++;
    }

    public boolean isFinished() {
        return roundIndex >= options.size();
    }
}

