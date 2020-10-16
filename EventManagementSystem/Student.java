package EventManagementSystem;

public class Student {
    private String studentID, firstName, lastName;
    private char sex;
    private int age;
    private Major major;

    //constructor
    public Student(String studentID, Major major, String firstName, String lastName, char sex, int age) {
        this.studentID = studentID; //length: 9 e.g. s12345678
        this.major = major; //length: 10
        this.firstName = firstName;  //length: 20
        this.lastName = lastName;  //length: 20
        this.sex = sex;
        this.age = age;
    }

    public String getStudentID() {
        return studentID;
    }

    public Major getMajor() {
        return major;
    }
    
    public String getMajorFullTitle() {
        return major.getMajorFullTitle();
    }

    public String printString() {
    	return String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3d|\n", studentID, firstName, lastName, sex, getMajorFullTitle(), age);
    }
}
