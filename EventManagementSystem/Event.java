package EventManagementSystem;

import java.util.ArrayList;
import java.util.Date;

public abstract class Event {

	private String eventID;
	private String eventName;
	private Date eventDate;
	protected int capacity;
	
	public Event(String eName, String eID, int cap, Date eDate) {
		eventName = eName;
		eventID = eID;
		capacity = cap;
		eventDate = eDate;
	}
	
	public String getEventID() {
		return this.eventID;
	}
	
	public abstract boolean isFull();

	public abstract void listJoinedStudent();

	public void printDetail() {
		System.out.printf("%s\t%s\t%s\t%d\t", eventID, eventName, eventDate.toString(), capacity);
	}
	
}
//remark: state pattern for different kind of event?
