package EventManagementSystem;

public class ExEventGroupIsFull extends Exception {
	
	public ExEventGroupIsFull() { super("Fail to join the event. The number of participated group of this event has reached its maximum.\n"); }
	public ExEventGroupIsFull(String msg) { super(msg); }
}
