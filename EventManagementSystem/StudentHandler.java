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
        System.out.printf("|%-9s|%-20s|%-20s|%-3s|%-10s|%-3s|", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
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

    public void createStudent(String studentID, String major, String firstName, String lastName, char sex, int age) throws ExStudentIDtooLong, ExMajorTooLong, ExFirstNameTooLong, ExLastNameTooLong, ExWrongSexInput {
        if (studentID.length() < 8) {
            throw new ExStudentIDtooLong();
        }
        if (major.length() < 10) {
            throw new ExMajorTooLong();
        }
        if (firstName.length() < 20) {
            throw new ExFirstNameTooLong();
        }
        if (lastName.length() < 20) {
            throw new ExLastNameTooLong();
        }
        if (!(sex == 'M' || sex == 'F')) {
            throw new ExWrongSexInput();
        }
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
