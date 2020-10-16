package EventManagementSystem;

public class ExDateFormatDay extends Exception {

	public ExDateFormatDay() { super("Invalid Date format - day, please enter 01 - 31!\n"); }
	public ExDateFormatDay(String msg) { super(msg); }
}
