package persistence;

import model.QuestionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderQuestionTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderQuestion reader = new JsonReaderQuestion("./data/noSuchFileQuestion.json");
        try {
            QuestionList questionList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmpty() {
        JsonReaderQuestion reader = new JsonReaderQuestion("./data/testReaderEmptyQuestion.json");
        try {
            QuestionList questionList = reader.read();
            assertEquals(0, questionList.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralQuestion() {
        JsonReaderQuestion reader = new JsonReaderQuestion("./data/testReaderGeneralQuestion.json");
        try {
            QuestionList questionList = reader.read();
            assertEquals("a", questionList.getQuestion(0));
            assertEquals("g", questionList.getQuestion(1));
            assertEquals(2, questionList.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
