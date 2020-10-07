package EventManagementSystem;

public class CmdDeleteEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
            EventAllocator eventAllocator = EventAllocator.getInstance();
            Event event = eventAllocator.findEventByID(cmdParts[2]);
            eventAllocator.deleteEvent(event);
        } catch (ExWrongCommand | ExEventNotFound e) {
            System.out.println(e.getMessage());
        } 
    }
}
