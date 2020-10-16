package EventManagementSystem;

public class ExEventNotFound extends Exception {

	public ExEventNotFound() { super("Event not found!\n"); }
	public ExEventNotFound(String msg) { super(msg); }
}
