package EventManagementSystem;

public class Student {
    private String studentID, major, firstName, lastName;
    private char sex;
    private int age;

    //constructor
    public Student(String studentID, String major, String firstName, String lastName, char sex, int age) {
        this.studentID = studentID;
        this.major = major;
        this.firstName = firstName;
        this.lastName = lastName;
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
        StringBuilder s = new StringBuilder();
        s.append(studentID).append(" ").append(firstName).append(" ").append(lastName).append(" ").append(sex).append(" ").append(major).append(" ").append(age);
        return s.toString();
    }
}
