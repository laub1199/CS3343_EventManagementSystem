package EventManagementSystem;

public class ExEventGroupMinNum extends Exception {
	public ExEventGroupMinNum() { super("Fail to join the event. The number of groupmate is less than the required minimum number of event.\n"); }
	public ExEventGroupMinNum(String msg) { super(msg); }
}
