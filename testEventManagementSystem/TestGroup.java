package testEventManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.*;
import static org.junit.jupiter.api.Assertions.*;
import EventManagementSystem.*;

public class TestGroup {
    private GroupHandler groupHandler;

    @BeforeEach
    public void setUp(){
        groupHandler = GroupHandler.getInstance();
    }

    @AfterEach
    public void tearDown() {
    }


    @Test   //test can get group by id
    public void test_GetGroupID_Valid() throws ExGroupNotFound {
        Group group1 = new Group("g11111111", 2);
        groupHandler.createGroup(group1);

        Group result = groupHandler.getGroup("g11111111");
        assertEquals(group1, result);
    }
    @Test   //test cannot get group by id
    public void test_GetGroupID_NotValid() throws ExGroupNotFound {
        Group group1 = new Group("g11111111", 2);
        groupHandler.createGroup(group1);

        Exception e = assertThrows(ExGroupNotFound.class, () -> groupHandler.getGroup("g11111110"));
        assertEquals("Group not found!\n", e.getMessage());
    }

    @Test   //test can get student number
    public void test_GetNumOfStudent_Valid() throws ExMajorNotFound {
        Group group1 = new Group("g11111111", 5);
        Student student1 = new Student("S11111111", Major.getMajor("cs"), "s1", "s1", 'F', 20);
        Student student2 = new Student("S22222222", Major.getMajor("cs"), "s2", "s1", 'F', 20);
        group1.addStudent(student1);
        group1.addStudent(student2);
        int result = group1.getNumOfStudent();
        assertEquals(2, result);
    }
    @Test   //test cannot get student number
    public void test_GetNumOfStudent_NotValid() throws ExMajorNotFound {
        Group group1 = new Group("g11111111", 5);
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        group1.addStudent(student1);
        int result = group1.getNumOfStudent();
        assertNotEquals(3, result);
    }
    @Test   //test can list students
    public void test_IsFoundStudentById_Valid() throws ExMajorNotFound {
        Group group1 = new Group("g11111111", 5);
        Student student2 = new Student("s22222222", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        group1.addStudent(student2);
        boolean result = group1.isFoundStudentById("s22222222");
        assertTrue(result);
    }
    @Test   //test cannot list students
    public void test_IsFoundStudentById_NotValid() throws ExMajorNotFound {

        Group group1 = new Group("g11111111", 5);
        Student student2 = new Student("s22222222", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        group1.addStudent(student2);
        boolean result = group1.isFoundStudentById("S11111111");
        assertFalse(result);
    }
}