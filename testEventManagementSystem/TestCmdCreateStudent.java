package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdCreateStudent {
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
    public void testCmdCreateStudent() throws Exception {
        String[] cmd = {"create", "student", "s00000005", "cm", "Mary", "Lam", "F", "20"};
        String result = new CmdCreateStudent().execute(cmd);
        assertEquals("Created student Mary Lam with StudentID: s00000005.", result);
    }

    @Test
    public void testWrongCommand() throws Exception {
        String[] cmd = {"create", "student", "s00000005", "cm", "Mary", "Lam", "F"};
        String result = new CmdCreateStudent().execute(cmd);
        String expected = "Wrong Command\n";
        expected += "Create student command should be \"create student sXXXXXXXXX\" <major> <first name> <last name> <gender> <age>.\n";
        assertEquals(expected, result);

    }

    @Test
    public void testWrongInput_WrongStudentIDFormat() throws Exception {
        String[] cmd = {"create", "student", "ss0000005", "cm", "Mary", "Lam", "F", "20"};
        String result = new CmdCreateStudent().execute(cmd);
        assertEquals("Invalid student ID!\n", result);
    }

    @Test
    public void testWrongInput_InvalidStudentID() throws Exception {
        String[] cmd = {"create", "student", "s05", "cm", "Mary", "Lam", "F", "20"};
        String result = new CmdCreateStudent().execute(cmd);
        assertEquals("Invalid student ID!\n", result);
    }

    @Test
    public void testWrongInput_FirstNameTooLong() throws Exception {
        String[] cmd = {"create", "student", "s00000005", "cm", "aaaaaaaaaaaaaaaaaaaaa", "Lam", "F", "20"};
        String result = new CmdCreateStudent().execute(cmd);
        assertEquals("First name cannot exceed 20 characters!\n", result);
    }

    @Test
    public void testWrongInput_LastNameTooLong() throws Exception {
        String[] cmd = {"create", "student", "s00000005", "cm", "Mary", "aaaaaaaaaaaaaaaaaaaaa", "F", "20"};
        String result = new CmdCreateStudent().execute(cmd);
        assertEquals("Last name cannot exceed 20 characters!\n", result);
    }

    @Test
    public void testWrongInput_WrongSexInput() throws Exception {
        String[] cmd = {"create", "student", "s00000005", "cm", "Mary", "Lam", "A", "20"};
        String result = new CmdCreateStudent().execute(cmd);
        assertEquals("Please input M or F for sex!\n", result);
    }

    @Test
    public void testWrongInput_StudentFound() throws Exception {
        String[] cmd = {"create", "student", "s00000001", "cm", "Mary", "Lam", "F", "20"};
        String result = new CmdCreateStudent().execute(cmd);
        assertEquals("Invalid student ID!\n", result);
    }

    @Test
    public void testWrongInput_WrongNumberFormat() throws Exception {
        String[] cmd = {"create", "student", "s00000005", "cm", "Mary", "Lam", "F", "A"};
        String result = new CmdCreateStudent().execute(cmd);
        assertEquals("Wrong number format!\n", result);
    }

    @Test
    public void testWrongInput_MajorNotFound() throws Exception {
        String[] cmd = {"create", "student", "s00000005", "xx", "Mary", "Lam", "F", "20"};
        String result = new CmdCreateStudent().execute(cmd);
        assertEquals("Major not found!\n", result);
    }
}
