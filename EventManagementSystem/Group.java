package EventManagementSystem;
import java.util.ArrayList;

public class Group{
	private String groupID;
	private ArrayList<Student> studentList = new ArrayList<Student>();
    private int numOfStudent;
    
    public Group(String groupID, ArrayList<Student> studentList, int numOfStudent){
        this.groupID = groupID;
        this.studentList = studentList;
        this. numOfStudent = numOfStudent;
    }


    public String getGroupID(){
        return groupID;
    }
	public int getNumOfStudent(){
        return this.numOfStudent;
    }       
	public void listStudentInGroup(){
        for(Student student:studentList){
            student.PrintString();
        }
    }     
	public ArrayList<Student> getStudentList(){
        return studentList;
    }
	public void addStudent(Student student){
        studentList.add(student);
    }
	public void addStudent(String studentID){
        studentList.add(getStudent(studentID));
    }
	public void deleteStudent(Student student){
        studentList.remove(student);
    }
	public void deleteStudent(String studentID){
        studentList.remove(getStudent(studentID));
    }
    public void setEvent(){
    }
}

