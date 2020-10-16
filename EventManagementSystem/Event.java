package EventManagementSystem;

import java.util.ArrayList;
import java.util.Date;

public abstract class Event {

	private String eventID;
	private String eventName;
	protected int capacity;
	private Day eventDate;
	//private String major;
	private Major major;

	public Event(String eName, String eID, int cap, Day eDate, Major maj) {
		eventName = eName;
		eventID = eID; //length: 9 e.g. e12345678
		capacity = cap; // cap > 0
		eventDate = eDate;
		major = maj;
	}
	
	public String getEventID() {
		return this.eventID;
	}
	
	public String getEventName() {
		return this.eventName;
	}
	
	public Major getMajor() {
		return this.major;
	}
	
	public String getMajorFullTitle(){
		return this.major.getMajorFullTitle();
	}
	
	public Day getEventDate() {
		return eventDate;
	}
	
	public abstract boolean isFull();

	public abstract void listJoinedStudent();
	
	public abstract ArrayList<Student> getStudentList();

	public String printDetail() {
		return String.format("|%-10s|%-30s|%-12s|%-8d|%-30s|",eventID,eventName,eventDate.toString(),capacity,getMajorFullTitle());
	}
	
	public boolean isStudentJoined(Student student) {
		if (getStudentList().contains(student)) 
			return true;
		return false;
	}
	
}
//remark: state pattern for different kind of event?
