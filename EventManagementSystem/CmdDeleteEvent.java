package EventManagementSystem;

public class CmdDeleteEvent implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
        try {
            if (cmdParts.length != 3 || cmdParts[2].charAt(0) != 'e') {
                throw new ExWrongCommand();
            }
            try {
            	String eID = cmdParts[2];
            	if (eID.length() != 9 || Integer.parseInt(eID.substring(1,8)) <0 || Integer.parseInt(eID.substring(1,8)) > 99999999) {
            		throw new ExInvalidEventID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidEventID();
            }
            EventAllocator eventAllocator = EventAllocator.getInstance();
            Event event = eventAllocator.findEventByID(cmdParts[2]);
            eventAllocator.deleteEvent(event);
            str = "Deleted event with EventID: " + cmdParts[2] + ".";
        } catch (ExEventNotFound | ExInvalidEventID e) {
        	str = e.getMessage();
        } catch (ExWrongCommand e) {
        	str = e.getMessage();
        	str += "Delete event command should be \"delete event eXXXXXXXXX\"\n";
    	}
        return str;
        
    }
}
