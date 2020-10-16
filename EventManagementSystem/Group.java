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
	public String listStudentInGroup(){
		String str = "";
        for(Student student:studentList){
        	str += student.printString();
        }
        return str;
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
	
	public boolean isFoundStudentById(String studentId) {
		for(Student student:studentList) {
			if(student.getStudentID().equals(studentId)) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteStudent(Student student){
        studentList.remove(student);
    }
    public String toString() {
    	return String.format("|%-10s|%-18s|%-23s|\n",groupID, getNumOfStudent(), maxNumOfStudent);
    	//return groupID + "\t" + getNumOfStudent() + "\t" +maxNumOfStudent;
    }
}

