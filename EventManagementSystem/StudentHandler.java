package EventManagementSystem;

import java.util.ArrayList;

public class StudentHandler {
    private static StudentHandler instance = new StudentHandler();
    private ArrayList<Student> studentList;
    private StudentHandler() {studentList = new ArrayList<>();}
    public static StudentHandler getInstance() {
        return instance;
    }

    public void listStudent() {
        System.out.println("Student list:");
        for (Student s: studentList) {
            System.out.println(s.printString());
        }
    }

    public Student getStudent(String studentID) {
        for (Student s: studentList) {
            if (s.getStudentID().equals(studentID)) {
                return s;
            }
        }
        return null;
    }

    public void createStudent(String studentID, String major, String firstName, String lastName, char sex, int age) {
        Student s = new Student(studentID, major, firstName, lastName, sex, age);
        studentList.add(s);
    }

    public void deleteStudent(Student student) {
        studentList.remove(student);
    }

    public void deleteStudent(String studentID) {
        studentList.removeIf(s -> s.getStudentID().equals(studentID));
    }
}
