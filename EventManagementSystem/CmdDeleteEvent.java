package EventManagementSystem;

public class CmdDeleteEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
            try {
            	String eID = cmdParts[2];
            	if (eID.length() != 9 || eID.charAt(0) != 'e') {
            		throw new ExInvalidEventID();
            	}
            	Integer.parseInt(eID.substring(1,8));
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidEventID();
            }
            EventAllocator eventAllocator = EventAllocator.getInstance();
            Event event = eventAllocator.findEventByID(cmdParts[2]);
            eventAllocator.deleteEvent(event);
            System.out.println("Deleted event with EventID: " + cmdParts[2] + ".");
        } catch (ExEventNotFound | ExInvalidEventID e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Delete event command should be \"delete event eXXXXXXXXX\"");
    	} 
    }
}
