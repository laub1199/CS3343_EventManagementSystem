package EventManagementSystem;

public class ExInvalidEventGroupCapacity extends Exception {
    public ExInvalidEventGroupCapacity() {super("Event Group's group capacity should be at least 1 and less than capacity.\n");}
}
