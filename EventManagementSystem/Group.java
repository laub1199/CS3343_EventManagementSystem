package EventManagementSystem;

import Student Student;

public class Group{
	private String groupID;
	private ArrayList<Student> studentList;
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
        for(String student:this.studentList){
            System.out.println(student);
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

