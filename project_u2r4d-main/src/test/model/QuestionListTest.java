package model;

import model.Question;
import model.QuestionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QuestionListTest {

    private QuestionList testListOfQuestions;
    private Question testQuestion;

    @BeforeEach
    void runBefore() {
        testListOfQuestions = new QuestionList();
        testQuestion = new Question("Who");
    }


    @Test
    void testConstructor() {
        assertEquals(testListOfQuestions.getSize(), 0);
    }

    @Test
    void testAddToListOfQuestions() {

        testListOfQuestions.addToListOfQuestions(testQuestion);
        assertEquals(1, testListOfQuestions.getSize());
        Question testQuestion2 = new Question("What");
        assertTrue(testListOfQuestions.isContained(testQuestion));
        testListOfQuestions.addToListOfQuestions(testQuestion2);
        assertEquals(2,testListOfQuestions.getSize());
        assertTrue(testListOfQuestions.isContained(testQuestion2));
        assertTrue(testListOfQuestions.isContained(testQuestion));
    }

    @Test
    void testGetQuestion(){
        testListOfQuestions.addToListOfQuestions(testQuestion);
        assertEquals("Who", testListOfQuestions.getQuestion(0));
        testListOfQuestions.addToListOfQuestions(new Question("Hello"));
        assertEquals("Hello", testListOfQuestions.getQuestion(1));
        assertEquals("Who", testListOfQuestions.getQuestion(0));
    }

    @Test
    void sameQuestion(){
        assertFalse(testListOfQuestions.addToListOfQuestions(testQuestion));
        testListOfQuestions.addToListOfQuestions(testQuestion);
        assertTrue(testListOfQuestions.addToListOfQuestions(testQuestion));
        testListOfQuestions.addToListOfQuestions(testQuestion);
        testListOfQuestions.addToListOfQuestions(new Question("Hi"));
        testListOfQuestions.addToListOfQuestions(new Question("Hi"));
        assertEquals(2, testListOfQuestions.getSize());
    }
    @Test
    void testRemoveQuestion(){
        testListOfQuestions.addToListOfQuestions(testQuestion);
        testListOfQuestions.addToListOfQuestions(new Question("Hi"));
        testListOfQuestions.removeQuestion(0);
        assertEquals(1, testListOfQuestions.getSize());
        testListOfQuestions.removeQuestion(0);
        assertEquals(0, testListOfQuestions.getSize());
    }

    @Test
    void testGetSize(){
        assertEquals(0, testListOfQuestions.getSize());
        testListOfQuestions.addToListOfQuestions(testQuestion);
        assertEquals(1, testListOfQuestions.getSize());
        testListOfQuestions.removeQuestion(0);
        testListOfQuestions.addToListOfQuestions(testQuestion);
    }
    @Test
    void testIsContained(){
        testListOfQuestions.addToListOfQuestions(testQuestion);
        assertTrue(testListOfQuestions.isContained(testQuestion));
        assertFalse(testListOfQuestions.isContained(new Question("hello")));
    }

    @Test
    void testAddAnswerList(){
        QuestionList testList = new QuestionList();
        testList.addToListOfQuestions(new Question("Me"));
        testListOfQuestions.addToListOfQuestions(testQuestion);

        testListOfQuestions.addQuestionList(testList);
        assertEquals(2,testListOfQuestions.getSize());
    }
}
