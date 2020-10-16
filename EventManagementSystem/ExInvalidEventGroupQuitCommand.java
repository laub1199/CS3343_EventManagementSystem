package EventManagementSystem;

public class ExInvalidEventGroupQuitCommand extends Exception {
    public ExInvalidEventGroupQuitCommand() {super("As the select event is a group event, please quit the group instead!\n");}
}
