package model;

import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class QuestionTest {
    private Question testQuestion;

    @BeforeEach
    void runBefore() {
        testQuestion = new Question("");
    }

    @Test
    void testConstructor(){
        assertEquals("", testQuestion.getQuestion());
    }


}