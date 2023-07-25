package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Represents a list of flashcard answers
public class AnswerList {
    private final List<Answer> listOfAnswers;

    // MODIFIES: this
    // EFFECTS: makes a new list of answers
    public AnswerList() {
        listOfAnswers = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an answer and adds the answers to the list
    public void addToListOfAnswer(Answer answer) {
        listOfAnswers.add(answer);
        EventLog.getInstance().logEvent(new Event("Answer was added to list: " + answer.getAnswer()));
    }

    // EFFECTS: returns listOfAnswers size
    public int getSize() {
        return listOfAnswers.size();
    }

    // EFFECTS: returns true if answer parameter is in list of answers
    //          false otherwise
    public boolean isContained(Answer answer) {
        return listOfAnswers.contains(answer);
    }

    // EFFECTS: returns answer at index
    public String getAnswer(int i) {
        return listOfAnswers.get(i).getAnswer();
    }

    // REQUIRES i >= 0 && i < listOfAnswers.getSize()
    // MODIFIES: this
    // EFFECTS: removes answer at index
    public void removeAnswer(int i) {
        EventLog.getInstance().logEvent(new Event("Answer was removed: " + getAnswer(i)));
        listOfAnswers.remove(i);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("answer", answersToJson());
        return json;
    }

    // EFFECTS: returns things in these answers as a JSON array
    private JSONArray answersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Answer a : listOfAnswers) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: combines two answer lists
    public List<Answer> addAnswerList(AnswerList answerList) {
        for (int i = 0; i < answerList.getSize(); i++) {
            listOfAnswers.add(new Answer(answerList.getAnswer(i)));
        }
        return listOfAnswers;
    }
}
