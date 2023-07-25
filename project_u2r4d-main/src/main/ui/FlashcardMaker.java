package ui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;

public class FlashcardMaker {
    // CITATION: Referenced elements of a text box from TextAreaDemo.java
    // from the phase 3 provided list of examples
    // https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
    // message after closing window
    private JTextArea question;
    private JTextArea answer;
    private JFrame frame;
    private NextCard nextCard;
    private QuestionList questionList;
    private AnswerList answerList;
    private QuestionList questionListParameter;
    private AnswerList answerListParameter;

    public FlashcardMaker(QuestionList questionListParameter, AnswerList answerListParameter) {
        createFrame();
        this.questionListParameter = questionListParameter;
        this.answerListParameter = answerListParameter;
        JPanel mainPanel = new JPanel();
        makeQuestionArea(mainPanel);
        makeAnswerArea(mainPanel);
        JButton nextButton = new JButton("Next Card");
        JButton viewButton = new JButton("View Cards");
        JButton quizButton = new JButton("Quiz Me!");
        JButton removeButton = new JButton("Remove Card");
        nextCard = new NextCard(question, answer);
        mainPanel.add(nextButton);
        nextButton.addActionListener(nextCard);
        addToLists();

        mainPanel.add(viewButton);
        mainPanel.add(quizButton);
        mainPanel.add(removeButton);

        viewButton.addActionListener(new ViewCard(this.questionList, this.answerList, frame, question));
        quizButton.addActionListener(new Quiz(this.questionList, this.answerList, frame));
        removeButton.addActionListener(new RemoveCard(this.questionList, this.answerList, frame));
        createSaveAndLoad();
        makeVisible(mainPanel);
        printClose();
    }


    // EFFECTS: creates the menu tab that contains save and load buttons
    public void createSaveAndLoad() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenu = new JMenuItem("Save");
        JMenuItem loadMenu = new JMenuItem("Load");

        fileMenu.add(saveMenu);
        fileMenu.add(loadMenu);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        saveMenu.addActionListener(new SaveMenuListener(nextCard.getQuestionList(), nextCard.getAnswerList()));
        loadMenu.addActionListener(new LoadMenuListener(nextCard.getQuestionList(), nextCard.getAnswerList()));
    }

    // EFFECTS makes the window visible
    public void makeVisible(JPanel mainPanel) {
        frame.getContentPane().add(mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    // EFFECTS: creates the question area
    public void makeQuestionArea(JPanel mainPanel) {
        question = new JTextArea(8, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(new Font("Calibri", Font.PLAIN, 20));

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
        JScrollPane answerScrollPane = new JScrollPane(answer);
        JLabel answerLabel = new JLabel("Answer");
        mainPanel.add(answerLabel);
        mainPanel.add(answerScrollPane);
    }


    // MODIFIES: this
    // EFFECTS: adds question and answer strings to the respective lists
    public void addToLists() {

        if (questionListParameter == null || answerListParameter == null) {
            this.questionList = nextCard.getQuestionList();
            this.answerList = nextCard.getAnswerList();
        } else {
            this.questionList = nextCard.getQuestionList();
            this.answerList = nextCard.getAnswerList();
            this.questionList.addQuestionList(questionListParameter);
            this.answerList.addAnswerList(answerListParameter);
        }
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

    // EFFECTS: creates JFrame
    public void createFrame() {
        frame = new JFrame("Flashcard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



