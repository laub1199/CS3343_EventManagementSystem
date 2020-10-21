package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestStudentHandler {

    @AfterEach
    public void tearDown() throws Exception {
        StudentHandler studentHandler = StudentHandler.getInstance();
        studentHandler.getStudentList().clear();
    }

    @Test
    public void testGetStudentList() throws Exception {
        ArrayList<Student> resultStudentList = new ArrayList<>();
        Student stu1 = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        Student stu2 = new Student("s00000002", Major.getMajor("cs"), "Peter", "Chan", 'M', 18);
        Student stu3 = new Student("s00000003", Major.getMajor("ds"), "Mark", "Kam", 'M', 18);
        Student stu4 = new Student("s00000004", Major.getMajor("ds"), "Ken", "Lee", 'M', 18);
        resultStudentList.add(stu1);
        resultStudentList.add(stu2);
        resultStudentList.add(stu3);
        resultStudentList.add(stu4);
        StudentHandler studentHandler = StudentHandler.getInstance();
        studentHandler.createStudent(stu1);
        studentHandler.createStudent(stu2);
        studentHandler.createStudent(stu3);
        studentHandler.createStudent(stu4);
        assertEquals(resultStudentList, studentHandler.getStudentList());
    }

    @Test
    public void testListStudent() throws Exception {
        StudentHandler studentHandler = StudentHandler.getInstance();
        studentHandler.createStudent(new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18));
        studentHandler.createStudent(new Student("s00000002", Major.getMajor("cs"), "Peter", "Chan", 'M', 18));
        String result = String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
        result += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3d|\n", "s00000001", "Sam", "Chow", "M", "Computer Science", 18);
        result += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3d|\n", "s00000002", "Peter", "Chan", "M", "Computer Science", 18);
        assertEquals(result, studentHandler.listStudent());
    }

    @Test
    public void testListStudent_emptyList() throws Exception {
        StudentHandler studentHandler = StudentHandler.getInstance();
        assertEquals("There are no student.", studentHandler.listStudent());
    }

    @Test
    public void testGetStudent() throws Exception {
        StudentHandler studentHandler = StudentHandler.getInstance();
        Student result = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        studentHandler.createStudent(result);
        assertEquals(result, studentHandler.getStudent("s00000001"));
    }

    @Test
    public void testCreateStudent() throws Exception {
        ArrayList<Student> resultStudentList = new ArrayList<>();
        Student stu1 = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        Student stu2 = new Student("s00000002", Major.getMajor("cs"), "Peter", "Chan", 'M', 18);
        resultStudentList.add(stu1);
        resultStudentList.add(stu2);
        StudentHandler studentHandler = StudentHandler.getInstance();
        studentHandler.createStudent(stu1);
        studentHandler.createStudent(stu2);
        assertEquals(resultStudentList, studentHandler.getStudentList());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        ArrayList<Student> resultStudentList = new ArrayList<>();
        Student stu1 = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        Student stu2 = new Student("s00000002", Major.getMajor("cs"), "Peter", "Chan", 'M', 18);
        resultStudentList.add(stu1);
        resultStudentList.add(stu2);
        resultStudentList.remove(stu2);
        StudentHandler studentHandler = StudentHandler.getInstance();
        studentHandler.createStudent(stu1);
        studentHandler.createStudent(stu2);
        studentHandler.deleteStudent(stu2);
        assertEquals(resultStudentList, studentHandler.getStudentList());
    }
}
