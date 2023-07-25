package ui;

import model.AnswerList;
import model.Event;
import model.EventLog;
import model.QuestionList;

import javax.swing.*;
import java.awt.*;

public class QuizRunner {

    private JTextArea answer;
    private JFrame frame;
    private JLabel questionLabel;
    private QuestionList questionList;
    private AnswerList answerList;

    public QuizRunner(QuestionList questionList, AnswerList answerList) {
        frame = new JFrame("Quiz");
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.questionList = questionList;
        this.answerList = answerList;
        JPanel mainPanel = new JPanel();
        makeQuestionLabel(mainPanel);
        makeAnswerArea();
        JScrollPane answerScrollPane = new JScrollPane(answer);
        JButton submitButton = new JButton("Submit Answer");
        JLabel spacing = new JLabel("                        ");
        spacing.setFont(new Font("Hi", Font.PLAIN, 70));
        mainPanel.add(spacing);
        mainPanel.add(answerScrollPane);
        mainPanel.add(submitButton);
        submitButton.addActionListener(new Submit(questionList, answerList, answer, questionLabel,
                submitButton, frame));
        makeSaveAndLoad();
        makeVisible(mainPanel);
        printClose();
    }

    // EFFECTS: makes the window visible
    public void makeVisible(JPanel mainPanel) {
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    // EFFECTS: makes question area
    public void makeQuestionLabel(JPanel mainPanel) {
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("", Font.PLAIN, 30));
        mainPanel.add(questionLabel);
    }

    // EFFECTS: makes the answer area writable
    public void makeAnswerArea() {
        answer = new JTextArea(8, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(new Font("Hi", Font.PLAIN, 20));

    }

    // EFFECTS: creates save and load file panels
    public void makeSaveAndLoad() {
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
