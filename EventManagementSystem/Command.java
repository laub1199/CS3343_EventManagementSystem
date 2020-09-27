package EventManagementSystem;

public interface Command {
        void execute(String[] cmdParts) throws CloneNotSupportedException;
}
