package EventManagementSystem;

public class ExDateFormatMonth extends Exception {

	public ExDateFormatMonth() { super("Invalid Date format - month, please enter a 3 letters english short form"); }
	public ExDateFormatMonth(String msg) { super(msg); }
}
