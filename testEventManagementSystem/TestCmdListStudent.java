package testEventManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import EventManagementSystem.*;

public class TestCmdListStudent {
    private StudentHandler studentHandler;

    @BeforeEach
    public void setUp(){
        studentHandler = StudentHandler.getInstance();
    }

    @AfterEach
    public void tearDown(){
    }

    @Test   //test if cmd length is not equal to 2
    public void test_CmdListStudent_01() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "student", "s11111111"};
        CmdListStudent cmdListStudent = new CmdListStudent();
        String result = cmdListStudent.execute(cmdPart);
        assertEquals("Wrong Command\nList student command should be \"list student\"\n", result);
    }

    @Test   //test if no student
    public void test_CmdListStudent_02() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "student"};
        CmdListStudent cmdListStudent = new CmdListStudent();
        String result = cmdListStudent.execute(cmdPart);
        assertEquals("There are no student.", result);
    }

    @Test   //test if no student
    public void test_CmdListStudent_03() throws CloneNotSupportedException, ExMajorNotFound {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        String[] cmdPart = {"list", "student"};
        CmdListStudent cmdListStudent = new CmdListStudent();
        String result = cmdListStudent.execute(cmdPart);
        assertEquals("|StudentID|First Name          |Last Name           |Sex|Major                         |Age|\n|s11111111|fn                  |ln                  |f  |Computer Science              |19 |\n", result);
    }
}
