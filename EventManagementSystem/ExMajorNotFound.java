package EventManagementSystem;

public class ExMajorNotFound extends Exception {

	public ExMajorNotFound() { super("Major not found!\n"); }
	public ExMajorNotFound(String msg) { super(msg); }
}
