package com.devops.backend.service.singleton;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Chatbot {
    private static Chatbot instance;
    private static final String prompt= """
            You are a helpful assistant specializing in delivery services for an online delivery web app.
            Your role is to assist users with tracking orders,\s
            answering questions about delivery policies, and providing estimates and instructions.
            You will receive text from users in a chat environment and respond with relevant, concise,\s
            and clear information, guiding them through the delivery process seamlessly.
            
            Type of packages that can be delivered: Fragile, Lithium Battery and Standard.
            Cost is based on package type and weight. Cost of weight is $10 per kg.
            User is allowed to modify the package type, weight, and delivery address.
            User receives notifications when the package is picked up, in transit, and delivered.
            User can easily track the package using the tracking number provided.
            To track a package, the user must must click on the 'My Packages' tab
            To access the notification center, the user must click on the "Notifications" tab
            To modify the user profile, the user must click on the 'Profile' tab
            To request a new delivery, the user must click on the 'Request a quote' in the 'Home' tab
            """;

    private Chatbot() {
        // private constructor to prevent instantiation
    }

    public static synchronized Chatbot getInstance() {
        if (instance == null) {
            instance = new Chatbot();
        }
        return instance;
    }

    public String getResponse(String message) {
        try {
            URL url = new URL(System.getenv("GEMINI_API"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode rootNode = objectMapper.createObjectNode();
            ArrayNode contentsArray = rootNode.putArray("contents");
            ObjectNode contentsNode = contentsArray.addObject();
            ArrayNode partsArray = contentsNode.putArray("parts");
            ObjectNode partsNode = partsArray.addObject();
            partsNode.put("text", prompt + "\nUser question: " + message);

            String jsonInputString = objectMapper.writeValueAsString(rootNode);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int code = connection.getResponseCode();
            if (code == 200) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode jsonResponse = mapper.readTree(response.toString());
                    JsonNode candidates = jsonResponse.get("candidates");
                    JsonNode firstCandidate = candidates.get(0);
                    JsonNode content = firstCandidate.get("content");
                    JsonNode parts = content.get("parts");
                    JsonNode firstPart = parts.get(0);
                    String text = firstPart.get("text").asText();
                    text = text.replace("\n", "\\\\n").replace("\r", "\\\\r").replace("\t", "\\\\t").replace("\"","'");

                    return "{\"response\": \"" + text + "\"}";
                }
            } else {
                return "{\"error\": \"Failed to get response from the chatbot service\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Exception occurred: " + e.getMessage() + "\"}";
        }
    }
}