package model;

import org.json.JSONObject;

// Represents a single flashcard question
public class Question {
    private String question;

    // MODIFIES: this
    // EFFECTS: creates a new instance of a question
    public Question(String question) {
        this.question = question;
    }

    // EFFECTS: returns question
    public String getQuestion() {
        return question;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        return json;
    }
}
