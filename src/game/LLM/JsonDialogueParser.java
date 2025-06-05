package game.LLM;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDialogueParser {

    public Dialogue parse(String jsonString) throws DialogueParsingException {
        try {
            JSONObject root = new JSONObject(jsonString);


            // Parse nested arrays into List<List<String>>
            List<List<String>> options = parseNestedStringArray(root.getJSONArray("options"));
            List<List<String>> responses = parseNestedStringArray(root.getJSONArray("responses"));

            return new Dialogue(options, responses);

        } catch (JSONException e) {
            throw new DialogueParsingException("Failed to parse Dialogue JSON", e);
        }
    }

    private List<List<String>> parseNestedStringArray(JSONArray outer) {
        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < outer.length(); i++) {
            JSONArray inner = outer.getJSONArray(i);
            List<String> row = new ArrayList<>();
            for (int j = 0; j < inner.length(); j++) {
                row.add(inner.getString(j));
            }
            result.add(row);
        }

        return result;
    }
}
