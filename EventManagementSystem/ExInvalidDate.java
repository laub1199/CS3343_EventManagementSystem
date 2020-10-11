package EventManagementSystem;

public class ExInvalidDate extends Exception {
    public ExInvalidDate() { super("Invalid Date"); }
    public ExInvalidDate(String msg) { super(msg); }
}
