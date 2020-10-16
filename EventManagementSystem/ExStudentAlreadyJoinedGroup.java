package EventManagementSystem;

public class ExStudentAlreadyJoinedGroup extends Exception {
	public ExStudentAlreadyJoinedGroup() { super("Fail to join the group as you already joined the group.\n"); }
	public ExStudentAlreadyJoinedGroup(String msg) { super(msg); }

}
