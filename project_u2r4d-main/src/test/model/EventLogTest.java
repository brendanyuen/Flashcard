package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private Event e1;
    private Event e2;
    private Event e3;
    private QuestionList questionList;
    private AnswerList answerList;

    @BeforeEach
    public void loadEvents() {
        e1 = new Event("A1");
        e2 = new Event("A2");
        e3 = new Event("A3");
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);
        el.logEvent(e2);
        el.logEvent(e3);
        questionList = new QuestionList();
        answerList = new AnswerList();
    }

    @Test
    public void testLogEvent() {
        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));
        assertTrue(l.contains(e3));
    }

    @Test
    public void testClear() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<Event> itr = el.iterator();
        assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }

    @Test
    void testQuestionList() {
        EventLog el = EventLog.getInstance();
        List<Event> l = new ArrayList<Event>();
        questionList.addToListOfQuestions(new Question("1"));
        for (Event next : el) {
            l.add(next);
        }
        assertTrue(l.toString().contains("Question was added to list: 1"));
        questionList.removeQuestion(0);
        for (Event next : el) {
            l.add(next);
        }
        assertTrue(l.toString().contains("Question was removed: 1"));
    }

    @Test
    void testAnswerList() {
        EventLog el = EventLog.getInstance();
        List<Event> l = new ArrayList<Event>();
        answerList.addToListOfAnswer(new Answer("1"));
        for (Event next : el) {
            l.add(next);
        }
        assertTrue(l.toString().contains("Answer was added to list: 1"));
        answerList.removeAnswer(0);
        for (Event next : el) {
            l.add(next);
        }
        assertTrue(l.toString().contains("Answer was removed: 1"));
    }
}

