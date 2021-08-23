package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    // @author Jonathan Cook
    // Reused from https://www.baeldung.com/java-testing-system-out-println
    // with minor modifications
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private TaskList taskList = new TaskList(new ArrayList<Task>(), new Storage());

    @Test
    void testAddTask() {
        assertEquals("Got it! I've added this task:\n  "
                + "[T][ ] homework\n"
                + "Now you have 1 task(s) in the list.",
                taskList.addTask(new ToDo("homework")));
    }

    @Test
    void testPrintTaskList() {
        taskList.addTask(new ToDo("homework"));
        taskList.printTaskList();
        assertEquals("Here are the tasks in your list:\n" +
                "1.[T][ ] homework",outputStreamCaptor.toString().trim());
    }

    @Test
    void testMarkTaskDone() {
        try {
            taskList.addTask(new ToDo("homework"));
            assertEquals("Good job! I've marked this task as done:\n  "
                            + "[T][X] homework",
                    taskList.markTaskDone(0));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testDdeleteTask() {
        try {
            taskList.addTask(new ToDo("homework"));
            assertEquals("Noted! I've removed this task:\n  "
                            + "[T][ ] homework\n"
                            + "Now you have 0 task(s) in the list.",
                            taskList.deleteTask(0));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testToDataFileInput() {
        taskList.addTask(new ToDo("homework"));
        assertEquals("T | 0 | homework\n",
                taskList.toDataFileInput());
    }

    @Test
    void testFilterByDate() {
        try {
            taskList.addTask(new Deadline("homework", LocalDate.parse("2021-08-23"),
                    LocalTime.parse("18:00")));
            assertEquals("Here is your list of task on this day:\n"
                            + "1.[D][ ] homework (by: Aug 23 2021 18:00)\n",
                    taskList.filterByDate(LocalDate.parse("2021-08-23")));
        } catch (DukeException e) {
            fail();
        }
    }
}