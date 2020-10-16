package EventManagementSystem;

public class ExNoSuitableEvent extends Exception {

    public ExNoSuitableEvent() { super("Sorry, there is no recommended event for you.\n"); }
    public ExNoSuitableEvent(String msg) { super(msg); }
}
