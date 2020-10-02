package EventManagementSystem;

public class ExStudentNotFound extends Exception {

	public ExStudentNotFound() { super("Student not found!"); }
	public ExStudentNotFound(String msg) { super(msg); }
}
