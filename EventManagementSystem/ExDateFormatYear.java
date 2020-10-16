package EventManagementSystem;

public class ExDateFormatYear extends Exception {

	public ExDateFormatYear() { super("Invalid Date format - month, year must be larger than 0\n"); }
	public ExDateFormatYear(String msg) { super(msg); }
}
