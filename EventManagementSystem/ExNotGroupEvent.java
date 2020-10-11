package EventManagementSystem;

public class ExNotGroupEvent extends Exception {
	public ExNotGroupEvent() { super("This is not a group event."); }
	public ExNotGroupEvent(String msg) { super(msg); }

}
