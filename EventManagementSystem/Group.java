package EventManagementSystem;
import java.util.ArrayList;

public class Group{
	private String groupID;
	private ArrayList<Student> studentList;
    private int numOfStudent;
    private EventGroup eventGroup;
    StudentHandler studentHandler = StudentHandler.getInstance();
    
    public Group(String groupID, int numOfStudent){
        this.groupID = groupID;
        this.studentList = new ArrayList<>(numOfStudent);
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
            student.printString();
        }
    }     
	public ArrayList<Student> getStudentList(){
        return studentList;
    }
	public void addStudent(Student student){
        studentList.add(student);
    }
	public void addStudent(String studentID) throws ExStudentNotFound{
        studentList.add(studentHandler.getStudent(studentID));
    }
	public void deleteStudent(Student student){
        studentList.remove(student);
    }
	public void deleteStudent(String studentID) throws ExStudentNotFound{
        studentList.remove(studentHandler.getStudent(studentID));
    }
    public String toString() {
    	if (eventGroup == null)
    		return groupID + "\t" + numOfStudent + "\t/\t/n";
    	else
    		return groupID + "\t" + numOfStudent + "\t" + eventGroup.getEventID() +"\t" + eventGroup.getEventName() + "\n";
    }
    
    public void setEventGroup(EventGroup event) {
    	eventGroup = event;
    }
    
    public EventGroup getEventGroup() {
    	return eventGroup;
    }
    
}

