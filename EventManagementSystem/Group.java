package EventManagementSystem;
import java.util.ArrayList;

public class Group{
	private String groupID;
	private ArrayList<Student> studentList;
    private int maxNumOfStudent;
    
    public Group(String groupID, int maxNumOfStudent){
        this.groupID = groupID;
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
	
	public boolean isFoundStudentById(String studentId) {
		for(Student student:studentList) {
			if(student.getStudentID() == studentId) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteStudent(Student student){
        studentList.remove(student);
    }
    public String toString() {
    	if (eventGroup == null)
    		return groupID + "\t" + numOfStudent + "\t/\t/n";
    	else
    		return groupID + "\t" + numOfStudent + "\t" + eventGroup.getEventID() +"\t" + eventGroup.getEventName() + "\n";
    }
}

