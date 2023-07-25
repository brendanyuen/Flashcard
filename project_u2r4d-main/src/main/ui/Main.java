package ui;

import model.AnswerList;
import model.QuestionList;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        // new FlashcardApp();


        SwingUtilities.invokeLater(new Runnable() {
            private AnswerList answerList = new AnswerList();
            private QuestionList questionList = new QuestionList();

            @Override
            public void run() {
                new FlashcardMaker(questionList, answerList);
            }
        });
    }
}
