package EventManagementSystem;

public class ExEventIndividualIsFull extends Exception {
	public ExEventIndividualIsFull() { super("Fail to join the event. The number of participant of this event has reached its maximum.\n"); }
	public ExEventIndividualIsFull(String msg) { super(msg); }

}
