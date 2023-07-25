package ui;

import model.AnswerList;
import model.QuestionList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Submit implements ActionListener {
    // Citation: https://stackoverflow.com/questions/8333802/displaying-an-image-in-java-swing
    // stack-overflow
    private final AnswerList answerList;
    private final JTextArea answer;
    private int score;
    private int index;
    private int indexQuestion;
    private final JLabel questionLabel;
    private final QuestionList questionList;
    private final JButton backToMaker;
    private int timesPressed;
    private final JFrame frame;
    private final String correct = "data/Correct-1.png";
    private final String incorrect = "data/Incorrect-1.png";


    public Submit(QuestionList questionList, AnswerList answerList, JTextArea answer, JLabel questionLabel,
                  JButton submitButton, JFrame frame) {
        this.answerList = answerList;
        this.answer = answer;
        score = 0;
        index = 0;
        indexQuestion = 0;
        this.questionList = questionList;
        this.questionLabel = questionLabel;
        this.questionLabel.setText(this.questionList.getQuestion(0));
        backToMaker = submitButton;
        timesPressed = 0;
        this.frame = frame;
    }

    // EFFECTS: checks if answer exactly matches user's answer; displays a check when correct and a crying
    // emoji when incorrect; on last card allows user to go back to flashcard maker
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (answer.getText().equals(answerList.getAnswer(index))) {
                score++;
                if (indexQuestion + 1 == questionList.getSize()) {
                    showImage(correct);
//                    questionList.getQuestion(indexQuestion);
                }
                changeCardIndex();
                showImage(correct);
            } else {

                if (indexQuestion + 1 == questionList.getSize()) {
                    showImage(incorrect);
//                    questionList.getQuestion(indexQuestion);
                }
                changeCardIndex();
                showImage(incorrect);
            }
        } catch (Exception exception) {
            handleLastCard();
        }
    }

    // EFFECTS: displays images for a check mark or sad face when correct/ incorrect
    public void showImage(String image) {
        try {
            BufferedImage img = ImageIO.read(new File(image));
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
            JOptionPane.showMessageDialog(null, label);

        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }


    // EFFECTS: Provides a score of the quiz and allows user to return to flashcard maker
    public void handleLastCard() {
        if (timesPressed == 0) {
            questionLabel.setText("");
            answer.setText("Score: " + score + " / " + questionList.getSize());
            timesPressed++;
            backToMaker.setText("Back to Flashcard Maker");
        } else {
            frame.setVisible(false);
            new FlashcardMaker(this.questionList, this.answerList);
        }
    }

    //    EFFECTS: adds index by one and resets answer for next question
    public void changeCardIndex() {
        index++;
        questionLabel.setText(questionList.getQuestion(index));
        answer.setText("");
        indexQuestion++;
    }
}
