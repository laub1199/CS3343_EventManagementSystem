package EventManagementSystem;

public class ExEventGroupMaxNum extends Exception {
	public ExEventGroupMaxNum() { super("Fail to join the event.The number of groupmate is more than the required maximum number of event.\n"); }
	public ExEventGroupMaxNum(String msg) { super(msg); }
}
