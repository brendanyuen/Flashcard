package ui;

import model.Answer;
import model.AnswerList;
import model.Question;
import model.QuestionList;
import persistence.JsonReaderAnswer;
import persistence.JsonWriterAnswer;

import persistence.JsonReaderQuestion;
import persistence.JsonWriterQuestion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Flashcard Application
public class FlashcardApp {
    private static final String JSON_STORE = "./data/listOfAnswer.json";
    private static final String JSON_STORE1 = "./data/listOfQuestion.json";

    private QuestionList questionList = new QuestionList();
    private AnswerList answerList = new AnswerList();

    private Scanner input = new Scanner(System.in);

    private String card;

    private JsonWriterAnswer jsonWriterAnswer;
    private JsonWriterQuestion jsonWriterQuestion;
    private JsonReaderAnswer jsonReaderAnswer;
    private JsonReaderQuestion jsonReaderQuestion;

    // Runs the flashcard application
    public FlashcardApp() {
        jsonWriterAnswer = new JsonWriterAnswer(JSON_STORE);
        jsonWriterQuestion = new JsonWriterQuestion(JSON_STORE1);
        jsonReaderAnswer = new JsonReaderAnswer(JSON_STORE);
        jsonReaderQuestion = new JsonReaderQuestion(JSON_STORE1);
        runFlashcard();
    }

    // MODIFIES: this
    // EFFECTS: allows users to add, have a test, remove, save and reload, or view questions and answers
    private void runFlashcard() {
        while (true) {
            questionDisplay();
            card = input.nextLine();
            if (card.equals("quit")) {
                break;
            } else if (card.equals("test")) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("\b");
                }
                testCard(card);
            } else if (card.equals("view")) {
                viewCard(card);
            } else if (card.equals("remove")) {
                removeCard(card);
            } else if (card.equals("save")) {
                saveFlashcard();
            } else if (card.equals("load")) {
                loadFlashcard();
            } else {
                getQuestionsAndAnswers();
            }
        }
    }


    // EFFECTS: gets questions and answers to add to flashcard
    private void getQuestionsAndAnswers() {
        addToListOfQuestions(card);
        answerDisplay();
        card = input.nextLine();
        addToListOfAnswers(card);
    }


    // MODIFIES: this
    // EFFECTS: adds question to a listOfQuestions
    private void addToListOfQuestions(String question) {
        Question question1 = new Question(question);
        while (questionList.addToListOfQuestions(question1)) {
            System.out.println("Question was already added, add a different question");
            card = input.nextLine();
            if (!questionList.addToListOfQuestions(new Question(card))) {
                questionList.addToListOfQuestions(new Question(card));
                break;
            }

        }
    }


    // MODIFIES: this
    // EFFECTS: adds answer to a listOfAnswers
    private void addToListOfAnswers(String answer) {
        Answer answer1 = new Answer(answer);
        answerList.addToListOfAnswer(answer1);
    }


    // EFFECTS: shows instruction for user to add, view, remove, or quit application
    private void questionDisplay() {
        System.out.println("Please write a question, quit, view, test, remove, save, or load");
    }

    // EFFECTS: asks the user to type an answer
    private void answerDisplay() {
        System.out.println("Please write the answer:");
    }

    // EFFECTS: allows the user to view questions and answers
    private void testCard(String card) {
        int score = 0;
        int total = answerList.getSize();
        for (int i = 0; i < questionList.getSize(); i++) {
            System.out.println("\n" + questionList.getQuestion(i));
            System.out.println("Type your answer:");
            card = input.nextLine();
            if (card.equals(answerList.getAnswer(i))) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect");
            }
        }
        System.out.println("You got: " + score + "/" + total);
    }

    // EFFECTS: allows user to view their questions and answers
    private void viewCard(String card) {
        for (int i = 0; i < questionList.getSize(); i++) {
            System.out.println("\n" + questionList.getQuestion(i));
            System.out.println("Type your enter anything for answer");
            card = input.nextLine();
            System.out.println(answerList.getAnswer(i));
        }
    }

    // REQUIRES: Integer.parse(card) >= 0 && Integer.parse(card) < questionList.getSize()
    // MODIFIES: this
    // EFFECTS: removes a question and answer
    private void removeCard(String card) {
        for (int i = 0; i < questionList.getSize(); i++) {
            System.out.println(i + " " + questionList.getQuestion(i) + "/" + answerList.getAnswer(i));
        }
        System.out.println("select the number you would like to remove");
        card = input.nextLine();
        if (Integer.parseInt(card) >= 0 && Integer.parseInt(card) <= questionList.getSize()) {
            questionList.removeQuestion(Integer.parseInt(card));
            answerList.removeAnswer(Integer.parseInt(card));
        } else {
            System.out.println("Invalid");
        }
    }

    // EFFECTS: saves the flashcard to file
    private void saveFlashcard() {
        try {
            jsonWriterAnswer.open();
            jsonWriterAnswer.write(answerList);
            jsonWriterAnswer.close();
            jsonWriterQuestion.open();
            jsonWriterQuestion.write(questionList);
            jsonWriterQuestion.close();
            System.out.println("Saved to " + JSON_STORE + "and " + JSON_STORE1);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE + "or" + JSON_STORE1);

        }
    }

    // MODIFIES: this
    // EFFECTS: loads flashcard from file
    private void loadFlashcard() {
        try {
            questionList = jsonReaderQuestion.read();
            answerList = jsonReaderAnswer.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
