package game.LLM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class GeminiService implements LLMService {
    private final String apiKey;
    private final String baseUrl;

    public GeminiService(String apiKey) {
        this.apiKey = apiKey;
        this.baseUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;
    }

    /**
     * Sends the prompt to Gemini and returns whatever Gemini replied (raw string).
     */
    @Override
    public String generateText(String prompt) {
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Build request body using org.json to ensure proper escaping.
            JSONObject part       = new JSONObject().put("text", prompt);
            JSONObject innerPart  = new JSONObject().put("parts", new org.json.JSONArray().put(part));
            JSONObject outer      = new JSONObject().put("contents", new org.json.JSONArray().put(innerPart));
            String     bodyString = outer.toString();

            // Write body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = bodyString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check HTTP status
            int status = connection.getResponseCode();
            BufferedReader reader;
            if (status == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            } else {
                // Read error details from error stream
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
            }

            StringBuilder responseBuilder = new StringBuilder();
            String        line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line.trim());
            }
            reader.close();

            // If bad status, return the raw error JSON (so you can inspect it).
            if (status != HttpURLConnection.HTTP_OK) {
                return "[HTTP " + status + "] " + responseBuilder.toString();
            }

            // Otherwise, extract Gemini’s nested “text” field and return it raw.
            return extractReplyText(responseBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return "[GeminiService error: " + e.getMessage() + "]";
        }
    }

    /**
     * Given Gemini’s full JSON response, pull out the “text” from candidates[0].content.parts[0].text.
     */
    private String extractReplyText(String response) {
        try {
            JSONObject json  = new JSONObject(response);
            String     reply = json.getJSONArray("candidates")
                    .getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .getString("text");
            // Strip any triple‐backtick wrappers if present
            return reply
                    .replaceAll("^\\s*[`']{3}json\\s*", "")
                    .replaceAll("[`']{3}\\s*$", "")
                    .trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "[Failed to extract reply text]";
        }
    }
}
