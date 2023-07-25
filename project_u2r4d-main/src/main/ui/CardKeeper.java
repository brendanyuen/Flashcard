package ui;

import model.Answer;
import model.AnswerList;
import model.Question;
import model.QuestionList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CardKeeper implements ActionListener {

    private JTextArea question;
    private JTextArea answer;
    private QuestionList questionList;
    private AnswerList answerList;
    private QuestionList newQuestionList;
    private AnswerList newAnswerList;
    private JFrame frame;

    public CardKeeper(QuestionList questionList, AnswerList answerList, JTextArea question, JTextArea answer,
                      JFrame frame) {
        this.questionList = questionList;
        this.answerList = answerList;
        this.question = question;
        this.answer = answer;
        newQuestionList = new QuestionList();
        newAnswerList = new AnswerList();
        this.frame = frame;
    }

    // EFFECTS: keeps displayed card in their lists
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (questionList.getSize() == 1) {
                newQuestionList.addToListOfQuestions(new Question(questionList.getQuestion(0)));
                newAnswerList.addToListOfAnswer(new Answer(answerList.getAnswer(0)));
                frame.setVisible(false);
                new FlashcardMaker(newQuestionList, newAnswerList);
            } else {
                question.setText(questionList.getQuestion(1));
                answer.setText(answerList.getAnswer(1));
                newQuestionList.addToListOfQuestions(new Question(questionList.getQuestion(0)));
                newAnswerList.addToListOfAnswer(new Answer(answerList.getAnswer(0)));
                questionList.removeQuestion(0);
                answerList.removeAnswer(0);
            }
        } catch (Exception ex) {
            frame.setVisible(false);
            new FlashcardMaker(newQuestionList, newAnswerList);
        }
    }

    public QuestionList getQuestionList() {
        return newQuestionList;
    }

    public AnswerList getAnswerList() {
        return newAnswerList;
    }
}
