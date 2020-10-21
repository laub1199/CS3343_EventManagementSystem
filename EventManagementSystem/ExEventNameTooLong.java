package EventManagementSystem;

public class ExEventNameTooLong extends Exception {
    public ExEventNameTooLong() { super("Event name cannot exceed 30 characters!\n"); }
}
