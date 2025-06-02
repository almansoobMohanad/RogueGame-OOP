package game.LLM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


import org.json.JSONObject;


public class GeminiService {

    private static final String API_KEY = "AIzaSyCTyI4LtvWd3ehYv586g-Sk38eDtBel30E"; // Replace this!
    private static final String REQUEST_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

    /**
     * Sends a prompt to Gemini and returns the plain text response.
     */
    public String callGemini(String prompt) {
        String result;
        try {
            URL url = new URL(REQUEST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "{\"contents\": [{\"parts\": [{\"text\": \"" + escapeJson(prompt) + "\"}]}]}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int code = connection.getResponseCode();
            System.out.println("Response Code: " + code);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                result = response.toString();

                // Just return raw response for now
                return result;
            }
        } catch (Exception e) {
            return "I am sorry, I cannot help you with that. Gemini offline at the moment.";
        }
    }

    /**
     * Escape quotes and backslashes for safe JSON insertion
     */
    private String escapeJson(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
