package persistence;

import model.Answer;
import model.AnswerList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderAnswerTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderAnswer reader = new JsonReaderAnswer("./data/noSuchFileAnswer.json");
        try {
            AnswerList answerList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmpty() {
        JsonReaderAnswer reader = new JsonReaderAnswer("./data/testReaderEmptyAnswer.json");
        try {
            AnswerList answerList = reader.read();
            assertEquals(0, answerList.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAnswer() {
        JsonReaderAnswer reader = new JsonReaderAnswer("./data/testReaderGeneralAnswer.json");
        try {
            AnswerList answerList = reader.read();
            assertEquals("a", answerList.getAnswer(0));
            assertEquals("g", answerList.getAnswer(1));
            assertEquals(2, answerList.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
