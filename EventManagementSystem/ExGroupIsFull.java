package EventManagementSystem;

public class ExGroupIsFull extends Exception {
	public ExGroupIsFull() { super("Fail to join the group. The group is full."); }
	public ExGroupIsFull(String msg) { super(msg); }

}
