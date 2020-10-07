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
    	if (studentList.size() > 0) {
	        System.out.printf("|%-9s|%-20s|%-20s|%-3s|%-10s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
	        for (Student s: studentList) {
	            System.out.println(s.printString());
	        }
	    }
        else {
			System.out.println("There are no student.");
        }
    }

    public Student getStudent(String studentID) throws ExStudentNotFound {
        for (Student s: studentList) {
            if (s.getStudentID().equals(studentID)) {
                return s;
            }
        }
        throw new ExStudentNotFound();
    }

    public void createStudent(Student student) {
        studentList.add(student);
    }

    public void deleteStudent(Student student) {
        studentList.remove(student);
    }

    public void deleteStudent(String studentID) throws NullPointerException {
        studentList.removeIf(s -> s.getStudentID().equals(studentID));
    }
}
