package model;

import model.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {

    private Answer testAnswer;

    @BeforeEach
    void runBefore() {
        testAnswer = new Answer("Hello");
    }

    @Test
    void testConstructor(){
        assertEquals("Hello", testAnswer.getAnswer());
    }

}
