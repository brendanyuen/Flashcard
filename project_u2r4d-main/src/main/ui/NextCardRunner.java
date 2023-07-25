package ui;

import model.Answer;
import model.AnswerList;
import model.Question;
import model.QuestionList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextCardRunner implements ActionListener {

    private QuestionList questionList;
    private AnswerList answerList;
    private JTextArea questionText;
    private int index;
    private int indexQuestion;
    private int indexAnswer;
    private JFrame frame;
    private JLabel cardSide;
    private JButton nextButton;



    public NextCardRunner(QuestionList questionList, AnswerList answerList, JTextArea question, JFrame frame,
                          JLabel cardSide, JButton nextButton) {

        this.frame = frame;
        this.cardSide = cardSide;
        this.nextButton = nextButton;
        this.questionList = questionList;
        this.answerList = answerList;

        questionText = question;
        question.setText(this.questionList.getQuestion(0));
        index = 1;
        indexQuestion = 1;
        indexAnswer = 0;

    }

    // EFFECTS: displays each card and returns to flashcard maker once you reach the end of the deck
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (rememberIndex() % 2 == 0) {
                questionText.setText(questionList.getQuestion(rememberIndexQuestion()));
                indexQuestion++;
                cardSide.setText("Question");
                nextButton.setText("See Answer");
            } else if (rememberIndex() % 2 == 1) {
                questionText.setText(answerList.getAnswer(rememberIndexAnswer()));
                indexAnswer++;
                cardSide.setText("Answer");
                nextButton.setText("Next Card");
            }
            index++;

        } catch (Exception ex) {
            frame.setVisible(false);
            new FlashcardMaker(questionList, answerList);

        }

    }

    // EFFECTS: returns the next card value
    public int rememberIndex() {
        return index;
    }

    // EFFECT: returns the next question point
    public int rememberIndexQuestion() {
        return indexQuestion;
    }

    // EFFECT: returns the next answer point
    public int rememberIndexAnswer() {
        return indexAnswer;
    }
}
