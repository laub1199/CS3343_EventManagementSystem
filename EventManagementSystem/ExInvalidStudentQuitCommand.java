package EventManagementSystem;

public class ExInvalidStudentQuitCommand extends Exception {
    public ExInvalidStudentQuitCommand() {super("Invalid params! Please enter studentQuit <studentID> <eventID/groupID>\n");}
}
