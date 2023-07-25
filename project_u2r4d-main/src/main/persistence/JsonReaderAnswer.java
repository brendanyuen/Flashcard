package persistence;

import model.Answer;
import model.AnswerList;
import model.Question;
import model.QuestionList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads list of answers from JSON data stored in file
public class JsonReaderAnswer {
    // Citation:
    // Based on the supplied Workroom example for CPSC 210, specifically JsonReader class
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/src/main/persistence

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderAnswer(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AnswerList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAnswerList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses AnswerList from JSON object and returns it
    private AnswerList parseAnswerList(JSONObject jsonObject) {
        AnswerList answerList = new AnswerList();
        addAnswers(answerList, jsonObject);
        return answerList;
    }

    // MODIFIES: ql
    // EFFECTS: parses answer from JSON object and adds them to flashcard
    private void addAnswers(AnswerList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("answer");
        for (Object json : jsonArray) {
            JSONObject nextAnswer = (JSONObject) json;
            this.addAnswer(al, nextAnswer);
        }
    }

    // MODIFIES: ql
    // EFFECTS: parses answer from JSON object and adds it to flashcard
    private void addAnswer(AnswerList al, JSONObject jsonObject) {
        String name = jsonObject.getString("answer");
        al.addToListOfAnswer(new Answer(name));
    }
}
