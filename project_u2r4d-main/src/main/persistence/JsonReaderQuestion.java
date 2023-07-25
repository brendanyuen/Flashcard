package persistence;

import model.EventLog;
import model.Question;
import model.QuestionList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReaderQuestion {
    // Citation:
    // Based on the supplied Workroom example for CPSC 210, specifically JsonReader class
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/src/main/persistence
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderQuestion(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public QuestionList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuestionList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses QuestionList from JSON object and returns it
    private QuestionList parseQuestionList(JSONObject jsonObject) {
        QuestionList questionList = new QuestionList();
        addQuestions(questionList, jsonObject);
        return questionList;
    }

    // MODIFIES: ql
    // EFFECTS: parses question from JSON object and adds them to flashcard
    private void addQuestions(QuestionList ql, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("question");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            this.addQuestion(ql, nextQuestion);
        }
    }

    // MODIFIES: ql
    // EFFECTS: parses question from JSON object and adds it to flashcard
    private void addQuestion(QuestionList ql, JSONObject jsonObject) {
        String name = jsonObject.getString("question");
        ql.addToListOfQuestions(new Question(name));
    }
}
