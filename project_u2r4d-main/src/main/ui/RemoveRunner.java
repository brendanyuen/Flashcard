package ui;

import model.AnswerList;
import model.Event;
import model.EventLog;
import model.QuestionList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveRunner {
    private JTextArea question;
    private JTextArea answer;
    private JFrame frame;
    private QuestionList questionList;
    private AnswerList answerList;

    public RemoveRunner(QuestionList questionList, AnswerList answerList) {
        frame = new JFrame("Flashcard Remover");
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.questionList = questionList;
        this.answerList = answerList;
        JPanel mainPanel = new JPanel();
        makeQuestionArea(mainPanel);
        makeAnswerArea(mainPanel);
        CardKeeper cardKeeper = new CardKeeper(questionList, answerList, question,
                answer, frame);
        JButton removeButton = new JButton("Remove Card");
        removeButton.addActionListener(new CardRemover(cardKeeper.getQuestionList(), cardKeeper.getAnswerList(),
                question, answer, frame, questionList, answerList));
        JButton keepButton = new JButton("Keep Card");
        keepButton.addActionListener(cardKeeper);
        mainPanel.add(removeButton);
        mainPanel.add(keepButton);
        makeSaveAndLoad();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
        printClose();
    }

    // EFFECTS: creates the question area
    public void makeQuestionArea(JPanel mainPanel) {
        question = new JTextArea(8, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(new Font("Calibri", Font.PLAIN, 20));
        question.setText(questionList.getQuestion(0));

        JScrollPane questionScrollPane = new JScrollPane(question);
        JLabel questionLabel = new JLabel("Question");
        mainPanel.add(questionLabel);
        mainPanel.add(questionScrollPane);
    }

    // EFFECTS: Creates the answer area
    public void makeAnswerArea(JPanel mainPanel) {
        answer = new JTextArea(8, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(new Font("Calibri", Font.PLAIN, 20));
        answer.setText(answerList.getAnswer(0));
        JScrollPane answerScrollPane = new JScrollPane(answer);
        JLabel answerLabel = new JLabel("Answer");
        mainPanel.add(answerLabel);
        mainPanel.add(answerScrollPane);
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
