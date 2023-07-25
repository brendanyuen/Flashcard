package persistence;

import model.Answer;
import model.AnswerList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterAnswerTest {

    @Test
    void testWriterInvalidFile() {
        try {
            AnswerList answerList = new AnswerList();
            JsonWriterAnswer writer = new JsonWriterAnswer("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmpty() {
        try {
            AnswerList answerList = new AnswerList();
            JsonWriterAnswer writer = new JsonWriterAnswer("./data/testWriterEmptyAnswer.json");
            writer.open();
            writer.write(answerList);
            writer.close();

            JsonReaderAnswer reader = new JsonReaderAnswer("./data/testWriterEmptyAnswer.json");
            answerList = reader.read();
            assertEquals(0, answerList.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAnswer() {
        try {
            AnswerList answerList = new AnswerList();
            answerList.addToListOfAnswer(new Answer("Me"));
            answerList.addToListOfAnswer(new Answer("Chocolate ice cream"));
            JsonWriterAnswer writer = new JsonWriterAnswer("./data/testWriterGeneralAnswer.json");
            writer.open();
            writer.write(answerList);
            writer.close();
            JsonReaderAnswer reader = new JsonReaderAnswer("./data/testWriterGeneralAnswer.json");
            answerList = reader.read();
            assertEquals(2, answerList.getSize());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
