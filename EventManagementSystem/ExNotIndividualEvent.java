package EventManagementSystem;

public class ExNotIndividualEvent extends Exception {
	public ExNotIndividualEvent() { super("This is not a individual event.\n"); }
	public ExNotIndividualEvent(String msg) { super(msg); }

}
