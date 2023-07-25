package ui;

import model.AnswerList;
import model.QuestionList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardRemover implements ActionListener {
    private QuestionList questionList;
    private AnswerList answerList;
    private JTextArea question;
    private JTextArea answer;
    private JFrame frame;
    private QuestionList newQuestionList;
    private AnswerList newAnswerList;

    public CardRemover(QuestionList questionList, AnswerList answerList, JTextArea question, JTextArea answer,
                       JFrame frame, QuestionList questionList1, AnswerList answerList1) {
        this.newQuestionList = questionList;
        this.newAnswerList = answerList;
        this.question = question;
        this.answer = answer;
        this.frame = frame;
        this.questionList = questionList1;
        this.answerList = answerList1;
    }


    // EFFECTS: removes displayed card's question/ answer
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (questionList.getSize() == 1) {
                questionList.removeQuestion(0);
                answerList.removeAnswer(0);
                frame.setVisible(false);
                new FlashcardMaker(newQuestionList, newAnswerList);
            } else {
                questionList.removeQuestion(0);
                answerList.removeAnswer(0);
                question.setText(questionList.getQuestion(0));
                answer.setText(answerList.getAnswer(0));
            }

        } catch (Exception exception) {
            frame.setVisible(false);
            new FlashcardMaker(questionList, answerList);
        }
    }


}
