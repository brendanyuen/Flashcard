package ui;

import model.Answer;
import model.AnswerList;
import model.Question;
import model.QuestionList;
import persistence.JsonReaderAnswer;
import persistence.JsonReaderQuestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadMenuListener implements ActionListener {

    private static final String JSON_STORE = "./data/listOfAnswer.json";
    private static final String JSON_STORE1 = "./data/listOfQuestion.json";


    private JsonReaderAnswer jsonReaderAnswer;
    private JsonReaderQuestion jsonReaderQuestion;

    private QuestionList questionList;
    private AnswerList answerList;

    private NextCard nextCard;

    public LoadMenuListener(QuestionList questionList, AnswerList answerList) {

        jsonReaderAnswer = new JsonReaderAnswer(JSON_STORE);
        jsonReaderQuestion = new JsonReaderQuestion(JSON_STORE1);
        this.questionList = questionList;
        this.answerList = answerList;
    }

    // MODIFIES: this
    // EFFECTS: loads flashcard from file
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            QuestionList quest = jsonReaderQuestion.read();
            AnswerList ans = jsonReaderAnswer.read();
//            for (int i = 0; i < quest.getSize(); i++) {
////                questionList.addToListOfQuestions(new Question(quest.getQuestion(i)));
////                answerList.addToListOfAnswer(new Answer(ans.getAnswer(i)));
//            }
            questionList.addQuestionList(quest);
            answerList.addAnswerList(ans);

        } catch (IOException ex) {
            int i = 0;
        }
    }
}
