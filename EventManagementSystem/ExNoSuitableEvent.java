package EventManagementSystem;

public class ExNoSuitableEvent extends Exception {

    public ExNoSuitableEvent() { super("There is no suitable event for you."); }
    public ExNoSuitableEvent(String msg) { super(msg); }
}
