package testEventManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import EventManagementSystem.*;

public class TestCmdListStudentJoinedGroup {
    private StudentHandler studentHandler;
    private GroupHandler groupHandler;

    @BeforeAll
    public static void init() throws ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear {
        SystemDate.createTheInstance("20-Oct-2020");
    }

    @BeforeEach
    public void setUp(){
        studentHandler = StudentHandler.getInstance();
        groupHandler = GroupHandler.getInstance();
    }

    @AfterEach
    public void tearDown(){
    }

    @Test   //test if cmd length is not equal to 3
    public void test_CmdListStudentJoinedGroup_01() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "studentJoinedGroup", "s11111111", "333"};
        CmdListStudentJoinedGroup cmdListStudentJoinedGroup = new CmdListStudentJoinedGroup();
        String result = cmdListStudentJoinedGroup.execute(cmdPart);
        assertEquals("Wrong Command\nList student joined group command should be \"list studentJoinedGroup sXXXXXXXX\"\n", result);
    }

    @Test   //test if student id is invalid (not start with "s")
    public void test_CmdListStudentJoinedGroup_02() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "studentJoinedGroup", "e11111111"};
        CmdListStudentJoinedGroup cmdListStudentJoinedGroup = new CmdListStudentJoinedGroup();
        String result = cmdListStudentJoinedGroup.execute(cmdPart);
        assertEquals("Wrong Command\nList student joined group command should be \"list studentJoinedGroup sXXXXXXXX\"\n", result);
    }

    @Test   //test if student id is invalid (length of student id is not equal to 9)
    public void test_CmdListStudentJoinedGroup_03() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "studentJoinedGroup", "s1111111"};
        CmdListStudentJoinedGroup cmdListStudentJoinedGroup = new CmdListStudentJoinedGroup();
        String result = cmdListStudentJoinedGroup.execute(cmdPart);
        assertEquals("Invalid student ID!\n", result);
    }

    @Test   //test if student id is invalid (student id less then 0)
    public void test_CmdListStudentJoinedGroup_04() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "studentJoinedGroup", "s-1111111"};
        CmdListStudentJoinedGroup cmdListStudentJoinedGroup = new CmdListStudentJoinedGroup();
        String result = cmdListStudentJoinedGroup.execute(cmdPart);
        assertEquals("Invalid student ID!\n", result);
    }

    @Test   //test if student join no group
    public void test_CmdListStudentJoinedGroup_05() throws CloneNotSupportedException, ExMajorNotFound {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        String[] cmdPart = {"list", "studentJoinedGroup", "s11111111"};
        CmdListStudentJoinedGroup cmdListStudentJoinedGroup = new CmdListStudentJoinedGroup();
        String result = cmdListStudentJoinedGroup.execute(cmdPart);
        assertEquals("|s11111111|fn                  |ln                  |f  |Computer Science              |19 |\nJoined Group:\n", result);
        studentHandler.deleteStudent(student1);
    }

    @Test   //test if student join one group
    public void test_CmdListStudentJoinedGroup_06() throws CloneNotSupportedException, ExMajorNotFound {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);
        Group group1 = new Group("g11111111", 5);
        groupHandler.createGroup(group1);
        group1.addStudent(student1);

        String[] cmdPart = {"list", "studentJoinedGroup", "s11111111"};
        CmdListStudentJoinedGroup cmdListStudentJoinedGroup = new CmdListStudentJoinedGroup();
        String result = cmdListStudentJoinedGroup.execute(cmdPart);
        assertEquals("|s11111111|fn                  |ln                  |f  |Computer Science              |19 |\nJoined Group:\n|g11111111 |1                 |5                      |\n", result);
        studentHandler.deleteStudent(student1);
        groupHandler.deleteGroup(group1);
    }

    @Test   //test if student join three group
    public void test_CmdListStudentJoinedGroup_07() throws CloneNotSupportedException, ExMajorNotFound {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);
        Group group1 = new Group("g11111111", 5);
        Group group2 = new Group("g22222222", 5);
        Group group3 = new Group("g33333333", 5);
        groupHandler.createGroup(group1);
        groupHandler.createGroup(group2);
        groupHandler.createGroup(group3);
        group1.addStudent(student1);
        group2.addStudent(student1);
        group3.addStudent(student1);

        String[] cmdPart = {"list", "studentJoinedGroup", "s11111111"};
        CmdListStudentJoinedGroup cmdListStudentJoinedGroup = new CmdListStudentJoinedGroup();
        String result = cmdListStudentJoinedGroup.execute(cmdPart);
        assertEquals("|s11111111|fn                  |ln                  |f  |Computer Science              |19 |\nJoined Group:\n|g11111111 |1                 |5                      |\n|g22222222 |1                 |5                      |\n|g33333333 |1                 |5                      |\n", result);
        studentHandler.deleteStudent(student1);
        groupHandler.deleteGroup(group1);
        groupHandler.deleteGroup(group2);
        groupHandler.deleteGroup(group3);
    }
}
