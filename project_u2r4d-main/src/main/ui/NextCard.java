package ui;

import model.Answer;
import model.AnswerList;
import model.Question;
import model.QuestionList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextCard implements ActionListener {

    private QuestionList questionList;
    private AnswerList answerList;
    private JTextArea questionUI;
    private JTextArea answerUI;

    public NextCard(JTextArea question, JTextArea answer) {
        questionUI = question;
        answerUI = answer;
        questionList = new QuestionList();
        answerList = new AnswerList();
    }


    // MODIFIES: this
    // EFFECTS: adds question and answer to their lists
    @Override
    public void actionPerformed(ActionEvent e) {
        questionList.addToListOfQuestions(new Question(questionUI.getText()));
        answerList.addToListOfAnswer(new Answer(answerUI.getText()));
        questionUI.setText("");
        answerUI.setText("");
        questionUI.requestFocus();
        answerUI.requestFocus();
    }

    // return list of questions
    public QuestionList getQuestionList() {
        return questionList;
    }

    // EFFECTS: returns list of answers
    public AnswerList getAnswerList() {
        return answerList;
    }
}
