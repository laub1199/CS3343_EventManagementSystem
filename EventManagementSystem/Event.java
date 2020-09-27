import java.util.ArrayList;

public abstract class Event {
	
	private String eventName;
	private String eventID;
	protected int capacity;
	//private Date eventDate;
	
	public String getEventID() {
		return this.eventID;
	}
	
	public abstract boolean isFull();

	public abstract void listJoinedStudent();
	
	
	
}
//remark: state pattern for different kind of event?
