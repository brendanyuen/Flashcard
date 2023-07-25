package persistence;

import model.Answer;
import model.AnswerList;
import model.Question;
import model.QuestionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterQuestionTest {
    @Test
    void testWriterInvalidFile() {
        try {
            QuestionList questionList = new QuestionList();
            JsonWriterQuestion writer = new JsonWriterQuestion("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmpty() {
        try {
            QuestionList questionList = new QuestionList();
            JsonWriterQuestion writer = new JsonWriterQuestion("./data/testWriterEmptyQuestion.json");
            writer.open();
            writer.write(questionList);
            writer.close();

            JsonReaderQuestion reader = new JsonReaderQuestion("./data/testWriterEmptyQuestion.json");
            questionList = reader.read();
            assertEquals(0, questionList.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralQuestion() {
        try {
            QuestionList questionList = new QuestionList();
            questionList.addToListOfQuestions(new Question("Me"));
            questionList.addToListOfQuestions(new Question("Chocolate ice cream"));
            JsonWriterQuestion writer = new JsonWriterQuestion("./data/testWriterGeneralQuestion.json");
            writer.open();
            writer.write(questionList);
            writer.close();
            JsonReaderQuestion reader = new JsonReaderQuestion("./data/testWriterGeneralQuestion.json");
            questionList = reader.read();
            assertEquals(2, questionList.getSize());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
