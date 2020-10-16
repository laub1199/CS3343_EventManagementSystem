package EventManagementSystem;

public class ExNotGroupEvent extends Exception {
	public ExNotGroupEvent() { super("This is not a group event.\n"); }
	public ExNotGroupEvent(String msg) { super(msg); }

}
