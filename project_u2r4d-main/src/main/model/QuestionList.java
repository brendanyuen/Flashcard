package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Represents a list of flashcard questions
public class QuestionList {

    private final List<Question> listOfQuestions;
    private Question question;

    // MODIFIES: this
    //  EFFECTS: makes a new listOfQuestions
    public QuestionList() {
        listOfQuestions = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: sent question gets added to the question to the list returns true
    //          if it contains question already does not add to list and returns false
    public boolean addToListOfQuestions(Question question) {
        boolean inList = false;
        for (Question listOfQuestion : listOfQuestions) {
            if (listOfQuestion.getQuestion().equals(question.getQuestion())) {
                inList = true;
                break;
            }
        }
        if (inList) {
            EventLog.getInstance().logEvent(new Event("Tried to add a duplicate question"));
        } else {
            EventLog.getInstance().logEvent(new Event("Question was added to list: " + question.getQuestion()));
            listOfQuestions.add(question);
        }

        return inList;
    }

    // EFFECTS: returns listOfQuestion size
    public int getSize() {
        return listOfQuestions.size();
    }

    // EFFECTS: returns true if question parameter is in list of questions
    //          false otherwise
    public boolean isContained(Question question) {
        return listOfQuestions.contains(question);
    }

    // EFFECTS: returns question at parameter index
    public String getQuestion(int i) {
        return listOfQuestions.get(i).getQuestion();
    }

    // REQUIRES i >= 0 && i < listOfQuestions.getSize()
    // MODIFIES: this
    // EFFECTS: removes question at index
    public void removeQuestion(int i) {
        EventLog.getInstance().logEvent(new Event("Question was removed: "
                + getQuestion(i)));
        listOfQuestions.remove(i);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", questionsToJson());
        return json;
    }

    // EFFECTS: returns things in these questions as a JSON array
    private JSONArray questionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Question q : listOfQuestions) {
            jsonArray.put(q.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: combines two question lists
    public List<Question> addQuestionList(QuestionList questionList) {
        for (int i = 0; i < questionList.getSize(); i++) {
            listOfQuestions.add(new Question(questionList.getQuestion(i)));
        }
        return listOfQuestions;
    }
}
