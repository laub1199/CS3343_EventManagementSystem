package EventManagementSystem;

public class CmdDeleteEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
            EventAllocator instance = EventAllocator.getInstance();
            instance.deleteEvent(cmdParts[2]);
        } catch (ExWrongCommand e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Wrong event ID!");
        }
    }
}
