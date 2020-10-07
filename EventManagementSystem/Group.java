package EventManagementSystem;
import java.util.ArrayList;

public class Group{
	private String groupID;
	private ArrayList<Student> studentList;
    private int maxNumOfStudent;
    
    public Group(String groupID, int maxNumOfStudent){
        this.groupID = groupID; //length: 9 e.g. g12345678
        this.studentList = new ArrayList<>();
        this.maxNumOfStudent = maxNumOfStudent;
    }
    public String getGroupID(){
        return groupID;
    }
	public int getNumOfStudent(){
        return studentList.size();
    }       
	public void listStudentInGroup(){
        for(Student student:studentList){
            student.printString();
        }
    }
    public int getMaxNumOfStudent() {
        return maxNumOfStudent;
    }
    public ArrayList<Student> getStudentList(){
        return studentList;
    }
	public void addStudent(Student student){
        studentList.add(student);
    }
	
	public boolean isFoundStudentById(String studentId) throws ExStudentNotFound{
		for(Student student:studentList) {
			if(student.getStudentID().equals(studentId)) {
				return true;
			}
		}
		throw new ExStudentNotFound();
	}
	
	public void deleteStudent(Student student){
        studentList.remove(student);
    }
    public String toString() {
    	return String.format("|%-10s|%-18s|%-23s|",groupID, getNumOfStudent(), maxNumOfStudent);
    	//return groupID + "\t" + getNumOfStudent() + "\t" +maxNumOfStudent;
    }
}

