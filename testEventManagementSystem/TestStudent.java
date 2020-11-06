package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStudent {

    @Test
    public void testGetStudentID() throws Exception {
        Student stu = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        assertEquals("s00000001", stu.getStudentID());
    }

    @Test
    public void testGetMajor() throws Exception {
        Student stu = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        assertEquals(Major.cs, stu.getMajor());
    }

    @Test
    public void testGetMajorFullTitle() throws Exception {
        Student stu = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        assertEquals("Computer Science", stu.getMajorFullTitle());
    }

    @Test
    public void testPrintString() throws Exception {
        Student stu = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        String result = String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3d|\n", "s00000001", "Sam", "Chow", "M", "Computer Science", 18);
        assertEquals(result, stu.printString());
    }
    
    @Test
    public void testCheckStudentID01() throws Exception {
        assertEquals(true, Student.checkStudentID("s00000001"));
    }
    
    @Test
    public void testCheckStudentID02() throws Exception {
        assertEquals(false, Student.checkStudentID("s000000001"));
    }
    
    @Test
    public void testCheckStudentID03() throws Exception {
        assertEquals(false, Student.checkStudentID("s!0000001"));
    }
    
    @Test
    public void testCheckStudentID04() throws Exception {
        assertEquals(false, Student.checkStudentID("s0000000t"));
    }
    
}
