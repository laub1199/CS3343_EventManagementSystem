package EventManagementSystem;

import java.util.ArrayList;
import java.util.Date;

public abstract class Event {

	private String eventID;
	private String eventName;
	protected int capacity;
	private Date eventDate;
	private String major;
	
	public Event(String eName, String eID, int cap, Date eDate, String maj) {
		eventName = eName;
		eventID = eID;
		capacity = cap;
		eventDate = eDate;
		major = maj;
	}
	
	public String getEventID() {
		return this.eventID;
	}
	
	public String getEventName() {
		return this.eventName;
	}
	
	public String getMajor() {
		return this.major;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	
	public abstract boolean isFull();

	public abstract void listJoinedStudent();
	
	public abstract ArrayList<Student> getStudentList();

	public void printDetail() {
		System.out.printf("%s\t%s\t%s\t%d\t%s\t", eventID, eventName, eventDate.toString(), capacity, major);
	}
	
}
//remark: state pattern for different kind of event?
