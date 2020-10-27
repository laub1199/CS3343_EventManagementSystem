package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdDeleteStudent {
    @BeforeEach
    public void setUp() throws Exception {
        StudentHandler studentHandler = StudentHandler.getInstance();

        // create students
        studentHandler.createStudent(new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18));
        studentHandler.createStudent(new Student("s00000002", Major.getMajor("cs"), "Peter", "Chan", 'M', 18));
        studentHandler.createStudent(new Student("s00000003", Major.getMajor("ds"), "Mark", "Kam", 'M', 18));
        studentHandler.createStudent(new Student("s00000004", Major.getMajor("ds"), "Ken", "Lee", 'M', 18));
    }

    @AfterEach
    public void tearDown() throws Exception {
        StudentHandler studentHandler = StudentHandler.getInstance();
        studentHandler.getStudentList().clear();
    }

    @Test
    public void testCmdDeleteStudent() throws Exception {
        String[] cmd = {"delete", "student", "s00000004"};
        String result = new CmdDeleteStudent().execute(cmd);
        assertEquals("Deleted student with StudentID: s00000004.", result);
    }

    @Test
    public void testWrongCommand() throws Exception {
        String[] cmd = {"delete", "student"};
        String result = new CmdDeleteStudent().execute(cmd);
        String expected = "Wrong Command\n";
        expected += "Delete student command should be \"delete student sXXXXXXXXX\"\n";
        assertEquals(expected, result);
    }

    @Test
    public void testWrongInput_InvalidStudentID() throws Exception {
        String[] cmd = {"delete", "student", "s04"};
        String result = new CmdDeleteStudent().execute(cmd);
        assertEquals("Invalid student ID!\n", result);
    }

    @Test
    public void testWrongInput_WrongNumberFormat() throws Exception {
        String[] cmd = {"delete", "student", "ss0000004"};
        String result = new CmdDeleteStudent().execute(cmd);
        assertEquals("Invalid student ID!\n", result);
    }

    @Test
    public void testWrongInput_StudentNotFound() throws Exception {
        String[] cmd = {"delete", "student", "s00000005"};
        String result = new CmdDeleteStudent().execute(cmd);
        assertEquals("Student not found!\n", result);
    }
}
