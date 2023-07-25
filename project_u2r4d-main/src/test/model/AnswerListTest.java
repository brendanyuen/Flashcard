package model;

import model.Answer;
import model.AnswerList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerListTest {

    private AnswerList testListOfAnswers;
    private Answer testAnswer;

    @BeforeEach
    void runBefore() {
        testListOfAnswers = new AnswerList();
        testAnswer = new Answer("Who");

    }

    @Test
    void testConstructor() {
        assertEquals(testListOfAnswers.getSize(), 0);
    }

    @Test
    void testAddOneQuestion() {
        assertEquals(0, testListOfAnswers.getSize());
        testListOfAnswers.addToListOfAnswer(testAnswer);
        assertEquals(1, testListOfAnswers.getSize());
    }

    @Test
    void testAddTwoQuestions(){
        testListOfAnswers.addToListOfAnswer(testAnswer);
        Answer testAnswer2 = new Answer("What");
        assertTrue(testListOfAnswers.isContained(testAnswer));
        testListOfAnswers.addToListOfAnswer(testAnswer2);
        assertEquals(2, testListOfAnswers.getSize());
        assertTrue(testListOfAnswers.isContained(testAnswer2));
        assertTrue(testListOfAnswers.isContained(testAnswer));
    }

    @Test
    void testGetAnswer(){
        testListOfAnswers.addToListOfAnswer(testAnswer);
        assertEquals("Who", testListOfAnswers.getAnswer(0));
        testListOfAnswers.addToListOfAnswer(new Answer("Hello"));
        assertEquals("Hello", testListOfAnswers.getAnswer(1));
    }
    @Test
    void testRemoveAnswer(){
        testListOfAnswers.addToListOfAnswer(testAnswer);
        testListOfAnswers.addToListOfAnswer(testAnswer);
        testListOfAnswers.removeAnswer(0);
        assertEquals(1, testListOfAnswers.getSize());
        testListOfAnswers.removeAnswer(0);
        assertEquals(0, testListOfAnswers.getSize());
    }
    @Test
    void testGetSize(){
        assertEquals(0, testListOfAnswers.getSize());
        testListOfAnswers.addToListOfAnswer(testAnswer);
        assertEquals(1, testListOfAnswers.getSize());
        testListOfAnswers.removeAnswer(0);
        testListOfAnswers.addToListOfAnswer(testAnswer);
    }
    @Test
    void testIsContained(){
        testListOfAnswers.addToListOfAnswer(testAnswer);
        assertTrue(testListOfAnswers.isContained(testAnswer));
        assertFalse(testListOfAnswers.isContained(new Answer("hello")));
    }

    @Test
    void testAddAnswerList(){
        AnswerList testList = new AnswerList();
        testList.addToListOfAnswer(new Answer("Me"));
        testListOfAnswers.addToListOfAnswer(testAnswer);

        testListOfAnswers.addAnswerList(testList);
        assertEquals(2,testListOfAnswers.getSize());
    }
}
