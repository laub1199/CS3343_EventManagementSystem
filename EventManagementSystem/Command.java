package EventManagementSystem;

public interface Command {
        String execute(String[] cmdParts) throws CloneNotSupportedException;
}