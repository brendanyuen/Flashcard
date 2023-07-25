package ui;

import model.AnswerList;
import model.QuestionList;
import persistence.JsonWriterAnswer;
import persistence.JsonWriterQuestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class SaveMenuListener implements ActionListener {

    private static final String JSON_STORE = "./data/listOfAnswer.json";
    private static final String JSON_STORE1 = "./data/listOfQuestion.json";

    private JsonWriterAnswer jsonWriterAnswer;
    private JsonWriterQuestion jsonWriterQuestion;
    private QuestionList questionList;
    private AnswerList answerList;

    public SaveMenuListener(QuestionList questionList, AnswerList answerList) {
        jsonWriterAnswer = new JsonWriterAnswer(JSON_STORE);
        jsonWriterQuestion = new JsonWriterQuestion(JSON_STORE1);
        this.questionList = questionList;
        this.answerList = answerList;
    }

    // EFFECTS: saves the flashcard to file
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            jsonWriterAnswer.open();
            jsonWriterAnswer.write(answerList);
            jsonWriterAnswer.close();
            jsonWriterQuestion.open();
            jsonWriterQuestion.write(questionList);
            jsonWriterQuestion.close();
            System.out.println("Saved to " + JSON_STORE + "and " + JSON_STORE1);
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to write to file: " + JSON_STORE + "or" + JSON_STORE1);

        }

    }
}
