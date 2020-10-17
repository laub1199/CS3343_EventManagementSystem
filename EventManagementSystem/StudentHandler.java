package EventManagementSystem;

import java.util.ArrayList;

public class StudentHandler {
    private static StudentHandler instance = new StudentHandler();
    private ArrayList<Student> studentList;
    private StudentHandler() {studentList = new ArrayList<>();}
    public static StudentHandler getInstance() {
        return instance;
    }

    public ArrayList<Student> getStudentList(){
    	return studentList;
    }
    
    public String listStudent() {
    	String str = "";
    	if (studentList.size() > 0) {
    		str = String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
	        for (Student s: studentList) {
	        	str += s.printString();
	        }
	    }
        else {
        	str = "There are no student.";
        }
    	return str;
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
    
}
