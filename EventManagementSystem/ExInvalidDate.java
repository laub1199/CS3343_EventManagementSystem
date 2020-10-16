package EventManagementSystem;

public class ExInvalidDate extends Exception {
    public ExInvalidDate() { super("Invalid Date\n"); }
    public ExInvalidDate(String msg) { super(msg); }
}
