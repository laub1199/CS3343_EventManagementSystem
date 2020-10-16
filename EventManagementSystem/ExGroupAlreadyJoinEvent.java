package EventManagementSystem;

public class ExGroupAlreadyJoinEvent extends Exception {
	
	public ExGroupAlreadyJoinEvent() { super("Fail to join the event as you already joined the event.\n"); }
	public ExGroupAlreadyJoinEvent(String msg) { super(msg); }

}
