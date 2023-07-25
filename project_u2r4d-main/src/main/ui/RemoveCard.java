package ui;

import model.AnswerList;
import model.QuestionList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCard implements ActionListener {

    private QuestionList questionList;
    private AnswerList answerList;
    private JFrame frame;

    public RemoveCard(QuestionList questionList, AnswerList answerList, JFrame frame) {
        this.questionList = questionList;
        this.answerList = answerList;
        this.frame = frame;
    }

    // EFFECTS: makes a remove card window
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            frame.setVisible(false);
            questionList.getQuestion(0);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new RemoveRunner(questionList, answerList);
                }
            });
        } catch (Exception ex) {
            new FlashcardMaker(questionList, answerList);
            JOptionPane.showMessageDialog(null, "List is empty",
                    "Add to List", JOptionPane.ERROR_MESSAGE);
        }
    }
}
