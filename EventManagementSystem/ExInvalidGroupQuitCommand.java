package EventManagementSystem;

public class ExInvalidGroupQuitCommand extends Exception {
    public ExInvalidGroupQuitCommand() {super("Invalid params! Please enter groupQuit <groupID> <groupEventID>\n");}
}
