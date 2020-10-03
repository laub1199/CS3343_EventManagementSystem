package EventManagementSystem;

public class Student {
    private String studentID, major, firstName, lastName;
    private char sex;
    private int age;

    //constructor
    public Student(String studentID, String major, String firstName, String lastName, char sex, int age) {
        this.studentID = studentID; //length: 8
        this.major = major; //length: 10
        this.firstName = firstName;  //length: 20
        this.lastName = lastName;  //length: 20
        this.sex = sex;
        this.age = age;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getMajor() {
        return major;
    }

    public String printString() {
        String s = String.format("|%-9s|%-20s|%-20s|%-3s|%-10s|%-3d|", studentID, firstName, lastName, sex, major, age);
        return s;
    }
}
