package model;

import org.json.JSONObject;

// Represents a single answer to a flashcard
public class Answer {

    private final String answer;

    // MODIFIES: this
    // EFFECTS: creates a new instance of an answer
    public Answer(String answer) {
        this.answer = answer;
    }

    // EFFECTS: returns answer
    public String getAnswer() {
        return answer;
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("answer", answer);
        return json;
    }
}
