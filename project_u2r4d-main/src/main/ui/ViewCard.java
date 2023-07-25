package ui;

import model.AnswerList;
import model.QuestionList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCard implements ActionListener {
    private QuestionList questionList;
    private AnswerList answerList;
    private JFrame frame;
    private JTextArea question;

    public ViewCard(QuestionList questionList, AnswerList answerList, JFrame frame,
                    JTextArea question) {
        this.questionList = questionList;
        this.answerList = answerList;
        this.frame = frame;
        this.question = question;
    }


    // EFFECTS: creates a flashcard view window
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            frame.setVisible(false);
            questionList.getQuestion(0);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new FlashcardRunner(questionList, answerList);
                }
            });
        } catch (Exception ex) {
            new FlashcardMaker(questionList, answerList);
            JOptionPane.showMessageDialog(null, "List is empty",
                    "Add to List", JOptionPane.ERROR_MESSAGE);
        }
    }
}
