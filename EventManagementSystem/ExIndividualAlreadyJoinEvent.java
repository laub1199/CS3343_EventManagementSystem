package EventManagementSystem;

public class ExIndividualAlreadyJoinEvent extends Exception {
	public ExIndividualAlreadyJoinEvent() { super("Fail to join the event as you already joined the event.\n"); }
	public ExIndividualAlreadyJoinEvent(String msg) { super(msg); }

}
