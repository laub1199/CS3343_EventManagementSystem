package EventManagementSystem;

public class ExGroupNotFound extends Exception {

	public ExGroupNotFound() { super("Group not found!\n"); }
	public ExGroupNotFound(String msg) { super(msg); }
}
