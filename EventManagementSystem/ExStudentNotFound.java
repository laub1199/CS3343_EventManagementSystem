package EventManagementSystem;

public class ExStudentNotFound extends Exception {

	public ExStudentNotFound() { super("Student not found!\n"); }
	public ExStudentNotFound(String msg) { super(msg); }
}
