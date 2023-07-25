package ui;

import model.AnswerList;
import model.Event;
import model.EventLog;
import model.QuestionList;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class FlashcardRunner {

    private JTextArea questionOrAnswer;
    private JFrame frame;


    public FlashcardRunner(QuestionList questionList, AnswerList answerList) {
        createFrame();

        JPanel mainPanel = new JPanel();

        JLabel cardSide = new JLabel("Question");
        mainPanel.add(cardSide);
        makeQuestionAndAnswerArea(mainPanel);
        JButton nextButton = new JButton("See Answer");


        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardRunner(questionList, answerList, questionOrAnswer, frame,
                cardSide, nextButton));


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenu = new JMenuItem("Save");
        JMenuItem loadMenu = new JMenuItem("Load");
        fileMenu.add(saveMenu);
        fileMenu.add(loadMenu);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        saveMenu.addActionListener(new SaveMenuListener(questionList, answerList));
        loadMenu.addActionListener(new LoadMenuListener(questionList, answerList));

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
        printClose();
    }

    // EFFECTS: makes a frame
    private void createFrame() {
        frame = new JFrame("Flashcard Viewer");
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
    }

    // EFFECTS: creates question area
    public void makeQuestionAndAnswerArea(JPanel mainPanel) {
        questionOrAnswer = new JTextArea(8, 20);
        questionOrAnswer.setLineWrap(true);
        questionOrAnswer.setWrapStyleWord(true);
        questionOrAnswer.setFont(new Font("Calibri", Font.PLAIN, 20));

        JScrollPane questionScrollPane = new JScrollPane(questionOrAnswer);
        mainPanel.add(questionScrollPane);
    }

    // EFFECTS: prints message after window closes
    public void printClose() {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString() + "\n\n");
                }
                //THEN you can exit the program
                System.exit(0);
            }
        });
    }


}
