package game.LLM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class GeminiService {
    private final String apiKey;
    private final String baseUrl;

    public GeminiService(String apiKey) {
        this.apiKey = apiKey;
        this.baseUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;
    }

    public JSONObject callGeminiJson(String prompt) {
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Add a clear instruction to reply only in JSON
            String fullPrompt = "You must reply ONLY in valid JSON. Do not include any other text. " + prompt;

            // Build the request body using org.json
            JSONObject message = new JSONObject();
            message.put("text", fullPrompt);

            JSONObject part = new JSONObject();
            part.put("parts", new org.json.JSONArray().put(message));

            JSONObject content = new JSONObject();
            content.put("contents", new org.json.JSONArray().put(part));

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = content.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }

                // Parse top-level response
                JSONObject responseJson = new JSONObject(response.toString());

                // Extract Gemini's actual JSON reply from its nested format
                String replyText = responseJson
                        .getJSONArray("candidates").getJSONObject(0)
                        .getJSONObject("content").getJSONArray("parts")
                        .getJSONObject(0).getString("text");

                // Parse Gemini's JSON reply
                return extractJsonObject(replyText);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Helpful for debugging
            return new JSONObject().put("reply", "[Gemini offline]").put("options", new String[0]);
        }
    }

    private JSONObject extractJsonObject(String responseText) {

        System.out.println("RESPONSE");
        System.out.println(responseText);
        System.out.println("//////////////////////////////");

        // Remove Markdown-style code block markers like ```json or '''json
        String cleaned = responseText
                .replaceAll("^\\s*[`']{3}json\\s*", "")  // remove opening ```json or '''json
                .replaceAll("[`']{3}\\s*$", "")          // remove closing ``` or '''
                .trim();

        try {
            return new JSONObject(cleaned);
        } catch (Exception e) {
            e.printStackTrace(); // helpful for debugging
            return new JSONObject().put("reply", "[Invalid JSON returned]").put("raw", cleaned);
        }
    }



}
